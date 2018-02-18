package math;

import java.math.BigInteger;
import java.util.List;

public class Pecahan {
	private BigInteger angka,pembilang,penyebut;

	public Pecahan(BigInteger angka) {
		super();
		this.angka = angka;
		pembilang=BigInteger.ZERO;
		penyebut=BigInteger.ONE;
	}

	public Pecahan(BigInteger angka, BigInteger pembilang, BigInteger penyebut) {
		super();
		this.angka = angka;
		this.pembilang = pembilang;
		this.penyebut = penyebut;
	}

	public BigInteger getAngka() {
		return angka;
	}

	public void setAngka(BigInteger angka) {
		this.angka = angka;
	}

	public BigInteger getPembilang() {
		return pembilang;
	}

	public void setPembilang(BigInteger pembilang) {
		this.pembilang = pembilang;
	}

	public BigInteger getPenyebut() {
		return penyebut;
	}

	public void setPenyebut(BigInteger penyebut) {
		this.penyebut = penyebut;
	}

	public boolean isSama(BigInteger p) {
		return isBulat()&&angka==p;
	}

	public boolean isBulat() {
		return pembilang==BigInteger.ZERO;
	}

	public boolean isSama(Pecahan p){
		return angka==p.angka&&pembilang==p.pembilang&&penyebut==p.penyebut;
	}

	public boolean isKurang(int p) {
		return angka.intValue()<p;
	}

	public boolean isKurang(Pecahan p) {
		Pecahan a=Pecahan.bongkar(this),b=Pecahan.bongkar(p);
		a.pembilang=a.pembilang.multiply(b.penyebut);
		b.pembilang=b.pembilang.multiply(a.penyebut);
		return -1==a.pembilang.compareTo(b.pembilang);
	}

	private static Pecahan bongkar(Pecahan p) {
		Pecahan c=new Pecahan(BigInteger.ZERO);
		c.penyebut=p.penyebut;
		c.pembilang=p.angka.multiply(p.penyebut).add(p.pembilang);
		return c;
	}

	public boolean isLebih(int p) {
		return angka.intValue()>=p||pembilang!=BigInteger.ZERO;
	}

	public boolean isLebih(Pecahan p) {
		Pecahan a=Pecahan.bongkar(this),b=Pecahan.bongkar(p);
		a.pembilang=a.pembilang.multiply(b.penyebut);
		b.pembilang=b.pembilang.multiply(a.penyebut);
		return 1==a.pembilang.compareTo(b.pembilang);
	}

	public boolean isKurangSama(int x) {
		return !isLebih(x);
	}

	public boolean isKurangSama(Pecahan p) {
		return !isLebih(p);
	}

	public boolean isLebihSama(int x) {
		return !isKurang(x);
	}

	public boolean isLebihSama(Pecahan p) {
		return !isKurang(p);
	}

	public Pecahan add(Pecahan p) {
		Pecahan a=Pecahan.bongkar(this),b=Pecahan.bongkar(p),c=new Pecahan(BigInteger.ZERO);
		c.pembilang=a.pembilang.multiply(b.penyebut).add(b.pembilang.multiply(a.penyebut));
		c.penyebut=a.penyebut.multiply(b.penyebut);
		return Pecahan.sederhana(c);
	}

	private static Pecahan sederhana(Pecahan p) {
		Pecahan c=new Pecahan(BigInteger.ZERO);
		c.angka=p.pembilang.divide(p.penyebut);
		c.penyebut=p.penyebut;
		c.pembilang=p.pembilang.mod(p.penyebut);
		for(BigInteger b:getPrime(c.pembilang))
			if(-1==b.compareTo(c.pembilang)&&-1==b.compareTo(c.penyebut))
				while(BigInteger.ZERO==c.pembilang.mod(b)&&BigInteger.ZERO==c.penyebut.mod(b)) {
			c.pembilang=c.pembilang.divide(b);
			c.penyebut=c.penyebut.divide(b);
		}return c;
	}

	private static List<BigInteger> getPrime(BigInteger b) {
		List<BigInteger>l=new java.util.LinkedList<>();
		for(BigInteger x=new BigInteger("2");-1==x.compareTo(b);x=x.add(BigInteger.ONE)) 
			if(x==new BigInteger("2")&&isPrime(x))l.add(x);
		return l;
	}

	private static boolean isPrime(BigInteger b) {
		for(BigInteger x=new BigInteger("2");-1==x.compareTo(b);x=x.add(BigInteger.ONE))
			if(BigInteger.ZERO==b.mod(x))return false;
		return true;
	}

	public Pecahan add(int x) {
		Pecahan p=new Pecahan(new BigInteger(""+x));
		return add(p);
	}

	public Pecahan sub(int x) {
		Pecahan p=new Pecahan(new BigInteger(""+x));
		return sub(p);
	}

	public Pecahan sub(Pecahan p) {
		Pecahan a=Pecahan.bongkar(this),b=Pecahan.bongkar(p),c=new Pecahan(BigInteger.ZERO);
		c.pembilang=a.pembilang.multiply(b.penyebut).subtract(b.pembilang.multiply(a.penyebut));
		c.penyebut=a.penyebut.multiply(b.penyebut);
		return Pecahan.sederhana(c);
	}

	public Pecahan mul(int x) {
		Pecahan p=new Pecahan(new BigInteger(""+x));
		return mul(p);
	}

	public Pecahan mul(Pecahan p) {
		Pecahan a=Pecahan.bongkar(this),b=Pecahan.bongkar(p),c=new Pecahan(BigInteger.ZERO);
		c.pembilang=a.pembilang.multiply(b.pembilang);
		c.penyebut=a.penyebut.multiply(b.penyebut);
		return Pecahan.sederhana(c);
	}

	public Pecahan div(int x) {
		Pecahan p=new Pecahan(new BigInteger(""+x));
		return div(p);
	}

	public Pecahan div(Pecahan p) {
		Pecahan a=Pecahan.bongkar(this),b=Pecahan.bongkar(p),c=new Pecahan(BigInteger.ZERO);
		c.pembilang=a.pembilang.multiply(b.penyebut);
		c.penyebut=a.penyebut.multiply(b.pembilang);
		return Pecahan.sederhana(c);
	}

	@Override
	public String toString() {
		if(!isBulat())return "(" + angka + ")" + pembilang + "/" + penyebut;
		else return ""+angka;
	}
}