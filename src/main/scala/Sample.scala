import person.{Gender, Person}

object Sample {
  def main(args: Array[String]): Unit = {

    val person = Person(
      name = "miral",
      age = 24,
      gender = Gender.FEMALE
    )
    println(person)
  }
}