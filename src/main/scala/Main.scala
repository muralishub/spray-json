//import MyJsonProtocol.jsonFormat2
import spray.json._

// we use this to convert to and from . this covers all the json value types




object Main extends App with DefaultJsonProtocol{

  // simple string to json
  val user = """{"user": {"name": "mike"}}"""
  val r: JsValue = user.parseJson


// json string to json
 val result: Object = try {
   user.parseJson
 } catch {
   case x: Exception => s"parse error: $x"
 }

//convert scala value type to json
  val list = List(1, 2, 3)
  val jsonList = list.toJson

//json to scala value type
  val obj = jsonList.convertTo[List[Int]]


  //custom types (using case classes) conversion
  val userObj = User("mike", "tim")

  implicit val userFormat = jsonFormat2(User)
  val userAsJson = userObj.toJson

  // pretty print
  println(userAsJson.prettyPrint)

//json to user type
  val jsonToObject = userAsJson.convertTo[User]



  //collections

  val userList = List(User("mike", "bramhal"), User("tony", "peter"))

  val userListToJson = userList.toJson


  //to list of users
 val usersList =  userListToJson.convertTo[List[User]]
  println(usersList)


  //another way to convert

  val otherWay = JsObject("name" -> JsString(userObj.firstName), "last" -> JsString(userObj.lastName))

}


case class User(firstName: String, lastName: String)
