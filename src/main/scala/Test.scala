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

    // class from copied java file. build it from newBuilder
    /*
    val wr: WriteRequest = WriteRequest(
      Seq(new TimeSeries(
        Seq(new Label("", "")),
        Seq(new Sample(1, 1500000L))
      )))
    */
    val ts = TimeSeries.newBuilder()
      .addLabels(Label.newBuilder().setName("myName").setValue("myValue"))
    val nwr: WriteRequest = WriteRequest.newBuilder()
      .addTimeseries(
        ts.addSamples(Sample.newBuilder().setTimestamp(15000).setValue(999))
          .addSamples(Sample.newBuilder().setTimestamp(16000).setValue(998))
      )
      .addTimeseries(
        TimeSeries.newBuilder()
          .addLabels(Label.newBuilder().setName("myName2").setValue("myValue2"))
          .addSamples(Sample.newBuilder().setTimestamp(17000).setValue(997))
          .addSamples(Sample.newBuilder().setTimestamp(18000).setValue(996))
      )
      .build()

    println(JsonFormat.printer().print(nwr))
    println(nwr.toByteArray)

    //    val httpclient: CloseableHttpClient = HttpClients.createDefault()
    //    val post = new HttpPost("")
    //    post.setEntity(new ByteArrayEntity(Snappy.compress(nwr.toByteArray)))
    //    val response: CloseableHttpResponse = httpclient.execute(post)
    //    println("status code: " + response.getStatusLine.getStatusCode)
  }
}
