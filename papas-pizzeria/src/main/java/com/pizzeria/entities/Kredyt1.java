package com.pizzeria.entities;

import org.springframework.stereotype.Component;

//Template component from lecture

@Component
public class Kredyt1 {
	
	public double obliczRate(double kwota, double procent, double rat) {
		double m = 1 - 1 / Math.pow(1.0 + procent / 12,  rat);
		double rata = kwota * (procent / 12) / m;
		
		return rata;
	}
}
