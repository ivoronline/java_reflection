package ivor.online;

import java.lang.reflect.*;

public class Main {

  //=========================================================================================================
  // MAIN
  //=========================================================================================================
  public static void main(String[] args) throws Exception {
    Class personClass = getClassInfo();
    constructors(personClass);
    methods     (personClass);
    properties  (personClass);
  }

  //=========================================================================================================
  // GET CLASS INFO
  //=========================================================================================================
  public static Class getClassInfo() throws ClassNotFoundException {

    //FROM OBJECT
    Person personObject = new Person("MyPassword", "John", 50);
    Class  personClass1 = personObject.getClass();

    //FROM CLASS PATH
    Class  personClass2 = Class.forName("ivor.online.Person");

    //FROM CLASS NAME (if Class is already loaded)
    Class  personClass3 = Person.class;

    //RETURN CLASS
    return personClass3;

  }

  //=========================================================================================================
  // CONSTRUCTORS
  //=========================================================================================================
  public static void constructors(Class personClass) throws Exception {

    //GET CONSTRUCTORS
    System.out.println("CONSTRUCTORS =============================");
    Constructor[] constructors = personClass.getConstructors();
    for(Constructor constructor : constructors) {
      System.out.print(constructor.getName() + "( ");
      for (Class parameterType : constructor.getParameterTypes()) {
        System.out.print(parameterType + ", ");
      }
      System.out.println(")");
    }

    //GET CONSTRUCTOR FROM THE LIST
    Constructor constructor0 = constructors[0];

    //GET CONSTRUCTOR BY PARAMETER TYPES
    System.out.println("GET CONSTRUCTOR BY PARAMETER TYPES =======");
    Class[]     parameterTypes = new Class[] {String.class, String.class, int.class};
    Constructor constructor1   = personClass.getDeclaredConstructor(parameterTypes);

    //CREATE OBJECT (Calls Constructor without arguments)
    System.out.println("CREATE OBJECT ============================");
    Person person1 = (Person) personClass.newInstance(); System.out.println(person1.toString());

    //CREATE OBJECT USING CONSTRUCTORS
    System.out.println("CREATE OBJECT USING CONSTRUCTOR ==========");
    Person      person2 = (Person) constructor0.newInstance();
    Person      person3 = (Person) constructor1.newInstance("MyPassword", "Susan", 20);
    System.out.println(person2.name);
    System.out.println(person3.name);

  }

  //=========================================================================================================
  // METHODS
  //=========================================================================================================
  public static void methods(Class personClass) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {

    //GET PUBLIC CHILD PARENT METHODS (including those from the Parent, no Constructors)
    System.out.println("PUBLIC CHILD PARENT METHODS ==============");
    Method[] publicChildParentMethods= personClass.getMethods();
    for(Method method : publicChildParentMethods) {
      System.out.println(method.getName());
    }

    //GET ALL METHODS (no Methods from the Parent, no Constructors)
    System.out.println("ALL METHODS ==============================");
    Method[] allMethods= personClass.getDeclaredMethods();
    for(Method method : allMethods) {
      System.out.println(method.getName());
    }

    //DISPLAY ONLY PUBLIC METHODS
    System.out.println("PUBLIC METHODS ===========================");
    for(Method method : allMethods) {
      if (Modifier.isPublic(method.getModifiers())) {
        System.out.println(method.getName());
      }
    }

    //GET METHOD FROM THE LIST
    Method method1 = allMethods[0];

    //GET METHOD BY NAME & PARAMETER TYPES
    Class[] parameterTypes = new Class[] {String.class, int.class};
    Method  method2        = personClass.getDeclaredMethod("sayHelloTo", parameterTypes);

    //DISPLAY METHOD INFO
    System.out.println("METHOD INFO ==============================");
    String  name      = method2.getName();              System.out.println(name);
    int     modifiers = method2.getModifiers();         System.out.println(modifiers);
    boolean isPublic  = Modifier.isPublic(modifiers);   System.out.println(isPublic);

    //DISPLAY METHOD PARAMETER TYPES
    System.out.println("METHOD PARAMETER TYPES ===================");
    parameterTypes = method2.getParameterTypes(); System.out.println(parameterTypes.toString());
    for(Class parameterType : parameterTypes) {
      System.out.println(parameterType.getName());
    }

    //DISPLAY RETURN TYPE
    System.out.println("METHOD RETURN TYPE =======================");
    Class returnType = method2.getReturnType();         System.out.println(returnType.getName());

    //CALL METHOD
    System.out.println("CALL METHOD ==============================");
    Person   personObject = new Person("MyPassword", "John", 50);
    Object[] params       = new Object[] { "Jill", 30 };
    String   hello1       = (String) method2.invoke(personObject, params);
    String   hello2       = (String) method2.invoke(personObject, "Susan", 40); //List of Parameters
    System.out.println(hello2);

  }

  //=========================================================================================================
  // PROPERTIES
  //=========================================================================================================
  public static void properties(Class personClass) throws IllegalAccessException {

    //GET PUBLIC PROPERTIES
    System.out.println("PUBLIC PROPERTIES ========================");
    Field[] publicFields= personClass.getFields();
    for(Field field : publicFields) {
      System.out.println(field.getName());
    }

    //GET ALL PROPERTIES
    System.out.println("ALL PROPERTIES ===========================");
    Field[] allFields= personClass.getDeclaredFields();
    for(Field field : allFields) {
      System.out.println(field.getName());
    }

    //DISPLAY ONLY PRIVATE METHODS
    System.out.println("PRIVATE PROPERTIES =======================");
    for(Field field : allFields) {
      if (Modifier.isPrivate(field.getModifiers())) {
        System.out.println(field.getName());
      }
    }

    //GET PROPERTY INFO
    Person  personObject = new Person("MyPassword", "John", 50);
    Field   property     = publicFields[0];
    String  name         = property.getName();
    int     modifiers    = property.getModifiers();
    boolean isPublic     = Modifier.isPublic(modifiers);

    //SET/GET PROPERTY VALUE
    System.out.println("SET/GET PROPERTY VALUE ===================");
    property.set(personObject, "John New");
    String value = (String) property.get(personObject);
    System.out.println(value);

  }

}


