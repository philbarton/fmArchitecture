class MyClass {
 
 private A a;
 private B b;
 private C c;
 
 MyClass(A a, B b, C c) {
    //Only Assignment
    this.a = a;
    this.b = b;
    this.c = c;
 }
 
 //Application Logic
 public void doSomething() {
    //Do A specific thing
    //Do B specific thing
    //Do C specific thing
 }
}
 
//The Factory
class MyFactory {
 public MyClass createMyClass() {
    return new MyClass(new AImpl(), new BImpl(), new CImpl());
 }
}
 
class Main {
 public static void main(String args[]) {
    MyClass mc = new MyFactory().createMyClass();
    mc.doSomething();
 }
}