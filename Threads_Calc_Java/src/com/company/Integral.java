package com.company;

public class Integral {
    
    private Function function;
    
    private double a;
    private double b;
    
    private double h;

    public Integral(Function function, double a, double b, double h) {
        this.function = function;
        this.a = a;
        this.b = b;
        this.h = h;
    }
    
    public double calc() {
        double sum = 0;
        for(double x = a+h; x <= b-h; x+=h) {
            sum += 2*function.fun(x);
        }
        sum += function.fun(a);
        sum += function.fun(b);
        return sum*h/2;
    }
}
