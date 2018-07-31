package com.epam.lisovyn.hard

import com.pi4j.io.gpio.GpioFactory
import com.pi4j.io.gpio.PinPullResistance
import com.pi4j.io.gpio.RaspiPin
import com.pi4j.io.gpio.event.GpioPinListenerDigital


class RaspListener {
    fun a() {

        val gpio = GpioFactory.getInstance()
        val myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN)

        myButton.setShutdownOptions(true)

        myButton.addListener(GpioPinListenerDigital { event ->
            println(" --> GPIO PIN STATE CHANGE: " + event.pin + " = " + event.state)
        })

        println(" ... complete the GPIO #02 circuit and see the listener feedback here in the console.")

        while (true) {
            Thread.sleep(500)
        }

         gpio.shutdown()
    }
}