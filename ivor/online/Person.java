package ivor.online;

public class Person {

  private String password;
  public  String name;
  public  int    age;

  public Person() {  }

  public Person(String password, String name, int age) {
    this.password = password;
    this.name     = name;
    this.age      = age;
  }

  private String getPassword() {
    return "Hello " + name;
  }

  public String getInfo() {
    return name + " is " + age + " years old";
  }

  public String sayHelloTo(String name, int age) {
    return "Hello " + name + ", you are " + age + " years old";
  }

}
