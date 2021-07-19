package io.bitsmart.bdd.report.utils.carbuilder.model;

import java.util.Objects;

public class Tire {
    final int size;
//    final int sectionWidthInMillimeters;
//    final int aspectRation;

    //205/60R15 91V
    //205 = Section Width in Millimeters
    //60 = Aspect Ration
    //    R = Radial Construction
    //15 = Rim diameter in Inches
    //91 = Load Index Service Description
    //    V = Speed Symbol


    public Tire(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tire)) return false;
        Tire wheel = (Tire) o;
        return size == wheel.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }

    @Override
    public String toString() {
        return "Wheel{" +
            "size=" + size +
            '}';
    }
}
