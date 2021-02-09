class ParkingLot {
    private lateinit var parkingSlot: MutableMap<Int, Array<String>>
    private var slotNumber = 0

    fun isSpaceInitialized() = ::parkingSlot.isInitialized

    fun createSpace(size: Int) {
        parkingSlot = mutableMapOf()
        slotNumber = 0
        if (size > 0) {
            repeat(size) {
                slotNumber++
                parkingSlot[slotNumber] = arrayOf("", "")
            }
            println("Created a parking lot with $size spots.")
        } else {
            println("$size is not a valid number..")
        }
    }

    fun park(plateNumber: String, color: String) {
        parkingSlot.forEach { (key, value) ->
            if (value[0] == "") {
                parkingSlot[key] = arrayOf(plateNumber, color)
                println("$color car parked in spot $key.")
                return
            }
        }
        println("Sorry, the parking lot is full.")
    }

    fun status() {
        val notNullable = parkingSlot.filterValues { it[0] != "" }
        val countSpace = notNullable.count()

        if (countSpace > 0) notNullable.forEach { (key, value) -> println("$key ${value[0]}") }
        else println("Parking lot is empty.")
    }

    fun leave(spotNum: Int) {
        if (spotNum in parkingSlot.keys) {
            parkingSlot[spotNum] = arrayOf("", "")
            println("Spot $spotNum is free.")
        } else {
            println("Space $spotNum does not exist.")
        }
    }

    fun regByColor(color: String) {
        parkingSlot.forEach { (_, value) ->
            if (color.equals(value[1], ignoreCase = true)) {
                println(parkingSlot
                        .filterValues { it[1].equals(color, ignoreCase = true) }
                        .values
                        .joinToString(", ") { it[0] }
                )
                return
            }
        }
        println("No cars with color $color were found.")
    }

    fun spotByColor(color: String) {
        parkingSlot.forEach { (_, value) ->
            if (color.equals(value[1], ignoreCase = true)) {
                println(parkingSlot
                        .filterValues { it[1].equals(color, ignoreCase = true) }
                        .keys
                        .joinToString(", ")
                )
                return
            }
        }
        println("No cars with color $color were found.")
    }

    fun spotByReg(plateNumber: String) {
        parkingSlot.forEach { (_, value) ->
            if (plateNumber == value[0]) {
                println(parkingSlot
                        .filterValues { it[0] == plateNumber }
                        .keys
                        .joinToString(", "))
                return
            }
        }
        println("No cars with registration number $plateNumber were found.")
    }
}

fun main() {
    val parking = ParkingLot()

    while(true) {
        val command = readLine()!!.split(" ")

        if(parking.isSpaceInitialized() || command[0] == "create" || command[0] == "exit")
            when (command[0]) {
                "create" -> parking.createSpace(command[1].toInt())
                "park" -> parking.park(command[1], command[2])
                "status" -> parking.status()
                "reg_by_color" -> parking.regByColor(command[1])
                "spot_by_color" -> parking.spotByColor(command[1])
                "spot_by_reg" -> parking.spotByReg(command[1])
                "leave" -> parking.leave(command[1].toInt())
                "exit" -> return
            }
        else println("Sorry, a parking lot has not been created.")
    }
}