class ParkingLot {
    private lateinit var parkSpace: MutableList<String?>
    fun isSpaceInitialized() = ::parkSpace.isInitialized
    fun createSpace(size: Int) {
        if (size > 0) {
            println("Created a parking lot with $size spots.")
            Array<String?>(size) { null }.toMutableList().also { parkSpace = it }
        } else {
            println("$size is not a valid number..")
        }
    }

    fun park(plateNumber: String, color: String) {
        for (i in 0..parkSpace.lastIndex) {
            if (parkSpace[i].isNullOrEmpty()) {
                println("$color car parked in spot ${i+1}.")
                parkSpace[i] = "$plateNumber $color"
                return
            }
        }
        println("Sorry, the parking lot is full.")
    }

    fun status() {
        if (parkSpace.filterNotNull().count() > 0) {
            parkSpace.forEachIndexed { index, element ->
                if (element != null) println("${index+1} $element")
            }
        } else {
            println("Parking lot is empty.")
        }
    }

    fun leave(spotNum: Int) {
        if (spotNum-1 in parkSpace.indices && parkSpace[spotNum - 1] != null) {
            println("Spot $spotNum is free.")
            parkSpace[spotNum - 1] = null
        } else {
            println("Space $spotNum does not exist.")
        }
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
                "leave" -> parking.leave(command[1].toInt())
                "exit" -> return
            }
        else println("Sorry, a parking lot has not been created.")
    }
}