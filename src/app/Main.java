package app;

import math.Pecahan;

public class Main {
	public static void main(String[]args) {
		Pecahan a=new Pecahan(java.math.BigInteger.ZERO),b=new Pecahan(java.math.BigInteger.ZERO),c;
		input(a);
		input(b);
		c=a.add(b);
		System.out.println(""+a+" + "+b+" = "+c);
		c=a.sub(b);
		System.out.println(""+a+" - "+b+" = "+c);
		c=a.mul(b);
		System.out.println(""+a+" * "+b+" = "+c);
		c=a.div(b);
		System.out.println(""+a+" + "+b+" = "+c);
	}

	private static void input(Pecahan p) {
		java.util.Scanner i=new java.util.Scanner(System.in);
		System.out.print("masukan angka : ");
		int a=i.nextInt();
		p.setAngka(new java.math.BigInteger(""+a));
		System.out.print("masukan pembilang : ");
		int b=i.nextInt();
		p.setPembilang(new java.math.BigInteger(""+b));
		System.out.print("masukan penyebut : ");
		int c=i.nextInt();
		p.setPenyebut(new java.math.BigInteger(""+c));
	}
}