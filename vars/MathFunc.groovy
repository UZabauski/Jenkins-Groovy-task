@Grab(group='org.apache.commons', module='commons-math3', version='3.0')
import org.apache.commons.math3.util.Precision

def javaFunc() {
    double a = Precision.round(Math.random(),3)
    println(a)
}
