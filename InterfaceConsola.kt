class InterfaceConsola(private val logica: SieteYMedia) {

    init {
        presentarJuego()
        jugar()
    }

    fun presentarJuego() {
        println("- El usuario es el jugador y el ordenador la banca.")
        println("- No hay en la baraja 8s y 9s. El 10 es la sota, el 11 el caballo y el 12 el Rey.")
        println("- Las figuras valen medio punto y el resto su valor.")
        println("- Primero juega el jugador.")
        println("- El jugador puede pedir cartas o plantarse.")
        println("- Si supera 7.5 pierde.")
        println("- Luego juega la banca, que debe empatar o superar al jugador sin pasarse.")
        println("\nEmpecemos!!!\n")
    }

    fun jugar() {
        turnoJugador()
        turnoBanca()
        println("Adios")

    }

    fun turnoJugador() {
        var opc = 'C'

        println("Como mínimo recibes una carta")

        while (logica.valorCartas(logica.getcartasJugador()) < 7.5 && opc == 'C') {

            val carta = logica.getbaraja().darCartas(1).first()
            logica.getcartasJugador().add(carta)

            println("Éstas son tus cartas jugador:")
            logica.mostrarCartas(logica.getcartasJugador())

            val valor = logica.valorCartas(logica.getcartasJugador())
            println("\n\tValor de cartas: $valor")

            if (valor < 7.5) {
                println("\n¿Pides [C]arta o te [P]lantas?")
                opc = readln().trim().uppercase()[0]
            }
        }
    }

    fun turnoBanca() {

        val valorJugador = logica.valorCartas(logica.getcartasJugador())

        if (valorJugador > 7.5) {
            println("Jugador, te has pasado, gana la banca")
            return
        }

        println("\nTurno de banca ...")

        while (logica.valorCartas(logica.getcartasBanca()) < valorJugador) {
            val carta = logica.getbaraja().darCartas(1).first()
            logica.getcartasBanca().add(carta)
        }

        println("Éstas son mis cartas:")
        logica.mostrarCartas(logica.getcartasBanca())

        val valor = logica.valorCartas(logica.getcartasBanca())
        println("\nValor de mis cartas: $valor")

        if (valor > 7.5)
            println("Me pasé, ganas tú jugador")
        else
            println("Gana la banca")
    }

}