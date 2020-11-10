package mx.tecnm.tepic.ladm_u2_juegodados

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var Juego=Hilo(this)
        var ejx=true
        btnr.setOnClickListener {
            try {
                if(ejx){
            Juego.start()
                ejx=false
                }
                else{
                    Juego.pausar()
                }
            }catch (e:Exception){}
        }
    }
}
class Hilo(p:MainActivity):Thread(){
    var puntero = p//Existe solo en esta linea.
    var mantener = true
    var despausado=true//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    fun terminarHilo(){
        mantener=false
    }
    fun pausar(){
        despausado=!despausado
    }
    override fun run(){
        super.run()
        //Realmente se ejecuta una vez en segundo plano.
        while(mantener) {
            if (despausado == true) {
                puntero.txtGN1.text="Jugador1"
                puntero.txtGN2.text="Jugador2"
                puntero.runOnUiThread {
                    var j1_1= Random.nextInt(1,6)///fUNCION QUE SAQUE NUMERO
                    var j1_2= Random.nextInt(1,6)
                    puntero.j1.setText("Dado1: "+j1_1 + "\nDado2: "+j1_2)
                    var j2_1= Random.nextInt(1,6)
                    var j2_2= Random.nextInt(1,6)
                    puntero.j2.setText("Dado1: "+j2_1 + "\nDado2: "+j2_2)

                    var sum1=j1_1+j1_2
                    var sum2=j2_1+j2_2
                    if(sum1==sum2){
                        puntero.txtGN1.text="Jugador1 EMPATE: "+sum1
                        puntero.txtGN2.text="Jugador2 EMPATE: "+sum2
                    }
                    else {
                        if (sum1 > sum2) {
                            puntero.txtGN1.text = "Jugador1 GANADOR " + sum1
                            puntero.txtGN2.text = "Jugador2 PERDEDOR " + sum2
                        } else {
                            puntero.txtGN1.text = "Jugador2 PERDEDOR " + sum1
                            puntero.txtGN2.text = "Jugador2 GANADOR " + sum2
                        }
                    }
                    despausado=false
                }

            }
            sleep(20)
        }
        sleep(20)
    }
}