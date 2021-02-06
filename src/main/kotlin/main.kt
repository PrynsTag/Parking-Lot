class ParkingLot() {
    lateinit var spots: MutableList<String?>

    fun isSpotsInitialized() = ::spots.isInitialized

    fun createSpots(size: Int): MutableList<String?> {
        println("Created a parking lot with $size spots.")
        return Array<String?>(size) { null }.toMutableList().also { spots = it }
    }

}

fun main() {
    var parking = ParkingLot()
    var spaces: MutableList<String?> = arrayListOf()

    first@ do {
        val input = readLine()!!.split(" ")

        if(parking.isSpotsInitialized() || input[0] == "create" || input[0] == "exit") {
            when (input[0]) {
                "create" -> {
                    if (input[1] > "0") {
                        parking = ParkingLot()
                        spaces = parking.createSpots(input[1].toInt())
                    }

                } "park" -> {
                for (i in 0..spaces.lastIndex) {
                    if (spaces[i].isNullOrEmpty()) {
                        println("${input[2]} car parked in spot ${i+1}.")
                        spaces[i] = "${input[1]} ${input[2]}"
                        continue@first
                    }
                }
                println("Sorry, the parking lot is full.")
                continue@first

            } "status" -> {
                if (spaces.filterNotNull().count() > 0) {
                    spaces.forEachIndexed { index, element ->
                        if (element != null) println("${index+1} $element")
                    }
                } else {
                    println("Parking lot is empty.")
                }

            } "leave" -> {
                println("Spot ${input[1]} is free.")
                spaces[input[1].toInt()-1] = null

            } "exit" -> {
                break@first
            }
            }
        } else println("Sorry, a parking lot has not been created.")
    } while (input[0] != "exit")
}