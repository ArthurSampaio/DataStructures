##1. Para que serve o conceito de tipos genéricos implementados por Java?

O Generics é uma extensão do Java que começou apartir do JDK 1.5 que permite ao programador possuir uma maior abstração dos tipos de objetos, ou seja, ao invés de fazer métodos para apenas um determinado tipo de objeto, com o generics, somos capazes de reutilizar o mesmo método diversas vezes com diversos objetos de tipos distintos. Isto permite uma aumento da coesão e da robustez do software. 


##2. Suponha que voce deseja definir uma interface A.java contendo um método m que recebe um objeto que pode ter diversos tipos (mas não pode ser diretamente do tipo Object). Implemente uma solução?


```java

public interface A{
	
	public void m(? obj){
	}
	

}

```

##3. Suponha que voce deseja estender a interface acima com uma sub-interface B.java que tem o método m funcionando especificamente para objetos do tipo String. Implemente uma solução?

```java

public interface B implements A{
	
	public void m(String obj){
	}
	

}

```

