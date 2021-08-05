@Library('my-shared-lib') _
import org.foo.MyClass

MyClass a = new MyClass()

pipeline {
    agent {
        label 'master'
    }
    options {
        skipDefaultCheckout()
        timestamps()
    }
    stages {
        stage('Get Echo') {
            steps {
                script {
                    EasyFunc.myFunc()
                }
            }
        }
        stage('Get Random') {
            steps {
                script {
                    MathFunc.javaFunc()
                }
            }
        }
        stage('Check class') {
            steps {
                script {
                    a.fValue = 3
                    a.sValue = 6

                    assert a.math() == 9
                    a.itWorks()
                }
            }
        }
    }
}
