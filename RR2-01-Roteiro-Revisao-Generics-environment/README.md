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

## 4. Seria possível criar uma classe C.java implementando a interface A.java e mesmo assim a implementação do método m na classe C poderia funcionar para diversos tipos de parâmetros? Implemente uma solução.

```java

public class C implements A{
	
	public void m(String obj){
		//TODO
	}
	
}

```


##5. O que acontece quando declaramos em uma classe parametrizada um método da seguinte forma: public void m(Collection<T> obj)?

O método acima diz respeito a um método parametrizado. Quando o objeto que contém este método for instanciado alterando o generics para algum tipo qualquer, automáticamente, o método "m" terá como parâmetro o tipo que foi alterado no generics na instanciação do objeto. 

##6. O que acontece quando declaramos em uma classe parametrizada um método da seguinte forma: public void m(Collection<?> obj)?

A interrogação (?) significa que o parâmetro do método m é uma coleção de tipos desconhecido, ou uma coleção de qualquer tipo de tipos. Contudo, há uma grande desvantagem, não é possível escrever código que se relacione com "obj", pois não é possível saber o tipo de ? e consequementemente não é possível determinar a hierarquia de ?.

##7. O que acontece quando declaramos em uma classe parametrizada um método da seguinte forma: public void m(? extends T obj)?

Em contraste com a questão anterior, o método m do método acima __recebe como parâmetro qualquer tipo que seja subtipo (herança) de T__, onde T é definido na instanciação da classe. 

##8. O que acontece quando declaramos em uma classe parametrizada um método da seguinte forma: public void m(? super T obj)?

Semelhante a questão 7, o método m recebe como parâmetro qualquer objeto que seja superclase do tipo genérico T. 


























