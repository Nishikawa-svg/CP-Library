package classes;

public class Complex {
	int real;
	int imag;
	public Complex(int real, int imag){
		this.real = real;
		this.imag = imag;
	}
	public Complex add(Complex c){
		return new Complex(this.real+c.real, this.imag+c.imag);
	}
	public Complex sub(Complex c){
		return new Complex(this.real-c.real, this.imag-c.imag);
	}
	public Complex mul(Complex c){
		int real = this.real*c.real - this.imag*c.imag;
		int imag = this.real*c.imag + this.imag*c.real;
		return new Complex(real, imag);
	}
	public int norm(){
		return (this.real*this.real + this.imag*this.imag);
	}
	public double abs(){
		return Math.sqrt(norm());
	}
	public double arg(){
		return Math.atan2(this.imag, this.real);
	}
	public double arg2(){
		if(imag < 0) return 2*Math.PI + Math.atan2(this.imag, this.real);
		else return Math.atan2(this.imag, this.real);
	}
	public double angle(){
		return Math.atan2(this.imag, this.real) / Math.PI * 180;
	}
	public double angle2(){
		return arg2() / Math.PI * 180;
	}
	@Override
	public String toString(){
		return "[" + this.real + ", " + this.imag + "]";
	}
}