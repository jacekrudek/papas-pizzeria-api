package com.pizzeria.entities;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;


//Template component from lecture

@Component
@Getter 
@Setter
public class Kredyt2 {
	private Double kwota;
	private Double procent;
	private Integer ilerat;
	private Double rata;
	
	public double obliczRate(double kwota, double procent, int ilerat) {
		this.kwota = kwota;
		this.procent = procent;
		this.ilerat = ilerat;
		double m = 1 - 1 / Math.pow(1.0 + procent / 12, ilerat);
		this.rata = kwota * (procent / 12) / m;
		
		return rata;
	}
}
