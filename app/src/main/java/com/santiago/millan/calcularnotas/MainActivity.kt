package com.santiago.millan.calcularnotas


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = findViewById(R.id.titulo1)
        contador = findViewById(R.id.counter)
        edicionT = findViewById(R.id.editText)
        nextButton = findViewById(R.id.next)
        instrucciones = findViewById(R.id.instructions)
        finishButton = findViewById(R.id.finish)
        mostrarNombre = findViewById(R.id.nombrE)
        mostrarP = findViewById(R.id.showP)
        cambio()
        //mostrarDatos()

        finishButton.setOnClickListener{
            mostrarDatos()
        }
    }
    lateinit var mostrarNombre : TextView
    lateinit var title: TextView
    lateinit var contador: TextView
    lateinit var edicionT: EditText
    lateinit var nextButton: Button
    lateinit var instrucciones: TextView
    lateinit var finishButton: Button
    lateinit var mostrarP : TextView
    var proceso = 0

    var listaEstudiantes: MutableList<Estudiante> = mutableListOf()

    var estudianteActual: Estudiante = Estudiante()

    var listaPorcentajes: MutableList<Int> = mutableListOf()


    var listaNotas: MutableList<Double> = mutableListOf()

    var porcentaje = 0
    var reset = 0

    fun validacion() {


        when (proceso) {
            0 -> {
                //crearEstudiante(edicionT.text.toString())
                estudianteActual.nombre = edicionT.text.toString()
                instrucciones.text = "Digite la nota:"
                edicionT.text.clear()

            }

            1 -> {
                val notaNueva =  edicionT.text.toString().toDouble()
                edicionT.text.clear()
                if(validarNota(notaNueva)){
                    instrucciones.text = "Digite el porcentaje:"
                    listaNotas.add(notaNueva)
                }else {
                    instrucciones.text = "Digite una nota valida:"
                    return
                }
                estudianteActual.notas = listaNotas.toTypedArray().toList()
            }


            2 -> {
                val porcentajeNuevo = edicionT.text.toString().toInt()
                edicionT.text.clear()

                if(validarPorcentaje(porcentajeNuevo)){
                    porcentaje += porcentajeNuevo
                    listaPorcentajes.add(porcentajeNuevo)
                    instrucciones.text = "Digite la nota:"
                    contador.text = "$porcentaje%"

                }else {
                    instrucciones.text = "Digite un porcentaje valido:"
                    return
                }
                estudianteActual.porcentajes = listaPorcentajes.toTypedArray().toList()
            }

            3 -> {  proceso = 1
                 return
                edicionT.text.clear()
            }
        }


        proceso++


        //mostrarDatos()
    }

    fun validarPorcentaje(pIngresado: Int): Boolean {
        return pIngresado + porcentaje <= 100

    }


    fun validarNota(nIngresada: Double) : Boolean{
        return (nIngresada <= 5.0) && (nIngresada >= 0.0)

    }




        fun cambio() {
            nextButton.setOnClickListener{
               contador.text = porcentaje.toString()
            validacion()

            }

        }
    fun mostrarDatos() {


        title.text = "Su resultado final es:"
        mostrarNombre.text = estudianteActual.nombre
        instrucciones.text = "Nota: " + estudianteActual.notaFinalR()
        mostrarP.text = "Promedio : " + estudianteActual.promedio()

        if(reset == 1){
            proceso = 0
            porcentaje = 0
            estudianteActual.nombre = ""
            estudianteActual.notas = listOf()
            estudianteActual.porcentajes = listOf()
            title.text = "!Calcula tu nota final¡"
            instrucciones.text = "Digite el nombre:"
            mostrarNombre.text = ""
            contador.text = "0%"
            mostrarP.text = ""
        }

        reset++


    }


    }




class Estudiante() {
    var nombre: String = ""
    var notas = listOf<Double>()
    var porcentajes = listOf<Int>()


    fun promedio(): Double {
        var division = 0
        var resultado2 : Double = 0.0

        for (p in notas){

            resultado2 += (p + notas [division]) / division
            division++
        }








        return resultado2

    }


        fun notaFinalR(): Double {
            var resultado: Double = 0.0


            var contador = 0
            var indice = 0
            for (n in notas) {

                resultado += (n * porcentajes[indice]) / 100
                indice++





                if (resultado > 5.0) {
                    println("El resultado final no es concluso, por favor revise la informacion digitada")
                } else if (resultado < 3.0) {

                    println("El estudiante reprobó" + resultado)
                } else if (resultado >= 3.0) {
                    println("el estudiante aprobó" + resultado)
                } else if (resultado < 0.0) {
                    println("El resultado final no es concluso, por favor revise la informacion digitada")
                }

            }










            return resultado

        }


    }