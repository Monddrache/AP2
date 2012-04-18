public static final Bruch ZERO = new Bruch(0);

public static final Bruch ONE = new Bruch(1, 1);

public boolean equals(Object einObjekt) {
	if (einObjekt instanceof Bruch) {
		Bruch einBruch = (Bruch) einObjekt; 
		if((this.zaehler == einBruch.zaehler) && (this.nenner == einBruch.nenner)){
			return true;
		};
	}
	return false;
}

public Bruch(long zahl) {
	this.zaehler = zahl;
	this.nenner = 1;
}

public Bruch(long zaehler, long nenner) {
	if (nenner == 0){
		throw new ArithmeticException();
	};
	long teiler = ggt(zaehler, nenner);
	long nen = (long)Math.sqrt((nenner/teiler)*(nenner/teiler));
	long zae = (long)Math.sqrt((zaehler/teiler)*(zaehler/teiler));
	if((zaehler < 0 && nenner < 0)||(zaehler > 0 && nenner > 0)){
		this.zaehler = zae;
		this.nenner = nen;
	}else if (zaehler > 0 && nenner < 0){
		this.zaehler = zae * -1;
		this.nenner = nen;
	}else{
		this.zaehler = zae * -1;
		this.nenner = nen;
	};
}

public Bruch multipliziere(Bruch b) {
	return new Bruch(zaehler * b.zaehler, nenner * b.nenner);
}

public Bruch dividiere(Bruch bruch2) {
	return new Bruch(zaehler * bruch2.nenner, bruch2.zaehler * nenner);
}

public Bruch kehrwert() {
	return new Bruch(nenner, zaehler);
}

public int compareTo(Bruch einBruch) {
	double ai = doubleValue();
	double bi = (double)einBruch.zaehler / (double)einBruch.nenner;
	if(ai > bi){
		return 1;
	}else if(ai < bi){
		return -1;
	}else if(ai == bi){
		return 0;
	}else{
		return 0;
	}
}

