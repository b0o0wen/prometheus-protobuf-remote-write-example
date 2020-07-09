import person.{Gender, Person}
import prometheus.Remote.WriteRequest
import prometheus.Types.{Label, Sample, TimeSeries}
import com.google.protobuf.util.JsonFormat
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.serializer.SerializeConfig
import org.apache.http.client.methods.{CloseableHttpResponse, HttpPost}
import org.apache.http.entity.{ByteArrayEntity, ContentType, StringEntity}
import org.apache.http.impl.client.{CloseableHttpClient, HttpClients}
import org.apache.http.util.EntityUtils
import org.xerial.snappy.Snappy

object Test {
  def main(args: Array[String]): Unit = {

    // class gen by scalaPB
    val person = Person(
      name = "miral",
      age = 24,
      gender = Gender.FEMALE
    )
    println(person)
    println(JSON.toJSONString(person, new SerializeConfig(true)))
    println(person.toByteArray)

    // class from copied java file. its constructor is private. build it from newBuilder
    /*
    val wr: WriteRequest = WriteRequest(
      Seq(new TimeSeries(
        Seq(new Label("", "")),
        Seq(new Sample(1, 1500000L))
      )))
    */
    val nwr: WriteRequest = WriteRequest.newBuilder().addTimeseries(
      TimeSeries.newBuilder()
        .addLabels(Label.newBuilder().setName("myName").setValue("myValue"))
        .addSamples(Sample.newBuilder().setTimestamp(15000).setValue(999))
    ).build()

    println(JsonFormat.printer().print(nwr))
    println(nwr.toByteArray)

    val httpclient: CloseableHttpClient = HttpClients.createDefault()
    val post = new HttpPost("http://vm.sh.agoralab.co/insert/8699/prometheus")
    post.setEntity(new ByteArrayEntity(Snappy.compress(nwr.toByteArray)))
    val response: CloseableHttpResponse = httpclient.execute(post)
    println("status code: " + response.getStatusLine.getStatusCode)
  }
}
