class MyClass {

 private A a; //A is an interface
 private B b; //B is an interface

 //Object creation within constructor
 MyClass() {  
    a = new AImpl(); //AImpl is the concrete impl of A
    b = new BImpl(); //BImpl is the concrete impl of B
 }

 //Application logic lies within this method
 public void doSomething() {
    //Do A specific thing
    //Do B specific thing
    C c = new CImpl(); //Object creation within the method.
    //Do C specific thing
 }
}