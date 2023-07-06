fun main() {

    val wildlife = Wildlife()

    println(wildlife.predatorsPrey("lion"))
    println(wildlife.predatorsPrey("Deer"))

    println(wildlife.animalLifespan("lion"))

    println(wildlife.trackMigration("lion"))
    println(wildlife.trackMigration("Elephant"))

    var story1 = Story()
    story1.addStory("born", "comic", 100, " good behaviors", "12-16", "English")
    println(story1.translate("born", "French"))

    val catalog = LibraryCatalog()

    catalog.addBook("Python Crash Course", "Eric Matthes", 5)
    catalog.addBook("Clean Code", "Robert C. Martin", 3)
    catalog.addBook("The Pragmatic Programmer", "Andrew Hunt and David Thomas", 2)

    println("Search by title: Python Crash Course")
    println(catalog.searchByTitle("Python Crash Course"))

    println("Search by author: Robert C. Martin")
    println(catalog.searchByAuthor("Robert C. Martin"))

    println("All books:")
    catalog.displayBookDetails()

    val product1 = Product("Book", 10.99, 3)
    val product2 = Product("Phone", 499.99, 1)
    val product3 = Product("Shirt", 29.99, 2)

    val totalValue1 = product1.calculateTotalValue()
    val totalValue2 = product2.calculateTotalValue()
    val totalValue3 = product3.calculateTotalValue()

    val totalValueOfAllProducts = totalValue1 + totalValue2 + totalValue3

    println("Total value of Product 1: $totalValue1")
    println("Total value of Product 2: $totalValue2")
    println("Total value of Product 3: $totalValue3")
    println("Total value of all products: $totalValueOfAllProducts")


    val student1 = Student("Alice", 20, listOf(80, 75, 90, 65, 70))
    val student2 = Student("Bob", 22, listOf(55, 60, 58, 62, 70))
    val student3 = Student("Carol", 19, listOf(75, 80, 85, 90, 92))

    student1.displayStudentInformation()
    println("Average Grade: ${student1.calculateAverageGrade()}")
    println("Pass Status: ${if (student1.hasPassed()) "Passed" else "Failed"}")
    println()

    student2.displayStudentInformation()
    println("Average Grade: ${student2.calculateAverageGrade()}")
    println("Pass Status: ${if (student2.hasPassed()) "Passed" else "Failed"}")
    println()

    student3.displayStudentInformation()
    println("Average Grade: ${student3.calculateAverageGrade()}")
    println("Pass Status: ${if (student3.hasPassed()) "Passed" else "Failed"}")
    val bookingSystem = FlightBooking()

    bookingSystem.addFlight("F101", "New York", "2023-07-10", 5)
    bookingSystem.addFlight("F102", "London", "2023-07-15", 3)
    bookingSystem.addFlight("F103", "Paris", "2023-07-20", 2)

    val availableFlights = bookingSystem.searchFlights("New York", "2023-07-10")
    println("Available Flights:")
    for (flight in availableFlights) {
        println("Flight Number: ${flight.flightNumber}, Destination: ${flight.destination}, Date: ${flight.date}, Available Seats: ${flight.availableSeats}")
    }

    val passenger1 = Passenger("Alice", 25)
    val passenger2 = Passenger("Bob", 30)

    val reservationSuccessful = bookingSystem.reserveSeat("F101", passenger1)
    if (reservationSuccessful) {
        println("Seat reserved for ${passenger1.name}")
    } else {
        println("Failed to reserve a seat")
    }

    val passengers = bookingSystem.getPassengers("F101")
    println("Passengers on Flight F101:")
    for (passenger in passengers) {
        println("Name: ${passenger.name}, Age: ${passenger.age}")
    }

    val confirmation = bookingSystem.generateBookingConfirmation("F101")
    println("Booking Confirmation for Flight F101:")
    println(confirmation)


}

// **Wildlife Preservation:** You're a wildlife conservationist working on a
// program to track different species in a national park. Each species has its own
// characteristics and behaviors, such as its diet, typical lifespan, migration
// patterns, etc. Some species might be predators, others prey. You'll need to
// create classes to model `Species`, `Predator`, `Prey`, etc., and think about how
// these classes might relate to each other through inheritance.
class Wildlife {
    var lifespan: Int = 0
    var preyMigration: Boolean = false
    val carnivore = listOf("lion", "leopard", "jaguar", "hyena")
    val omnivore = listOf("bears", "raccoons")
    val herbivore = listOf("sheep", "goat", "cow", "giraffe", "gazelle")
    val prey = mutableListOf<String>()
    val predator = mutableListOf<String>()
    var predatorMigration: Boolean = true

    fun predatorsPrey(animalName: String): String {
        return if (animalName in carnivore) {
            predator.add(animalName)
            "$animalName is a predator"
        } else {
            prey.add(animalName)
            "$animalName is a prey"
        }

    }

    fun animalLifespan(animalName: String): IntRange? {
        return when {
            animalName in carnivore -> 0..15
            animalName in omnivore -> 0..25
            animalName in herbivore -> 0..30
            else -> null
        }
    }

    fun trackMigration(animalName: String): String {
        return if (animalName in prey) {
            preyMigration = true
            if (preyMigration) {
                predatorMigration
                "$animalName is migrating"
            } else {
                predatorMigration = false
                ""
            }
        } else {
            predatorMigration = false
            preyMigration
            "$animalName is not found"
        }
    }
}


// **African Cuisine:** You're creating a recipe app specifically for African cuisine.
// The app needs to handle recipes from different African countries, each with its
// unique ingredients, preparation time, cooking method, and nutritional
// information. Consider creating a `Recipe` class, and think about how you might
// create subclasses for different types of recipes (e.g., `MoroccanRecipe`,
// `EthiopianRecipe`, `NigerianRecipe`), each with their own unique properties and
// methods.
class Recipes {
    private val recipes = mutableListOf<String>()
    private val ingredients = mutableMapOf<String, List<String>>()
    private val prepTime = mutableMapOf<String, String>()
    private val cookingMethod = mutableMapOf<String, String>()
    private val nutritionalInfo = mutableMapOf<String, String>()

    fun addRecipe(
            nameRecipe: String,
            listOfIngredients: List<String>,
            prepTime: String,
            cookingMethod: String,
            nutritionalInfo: String
    ): String {
        if (nameRecipe.isNotBlank() && listOfIngredients.isNotEmpty() && prepTime.isNotBlank() && cookingMethod.isNotBlank() && nutritionalInfo.isNotBlank()) {
            recipes.add(nameRecipe)
            ingredients[nameRecipe] = listOfIngredients
            this.prepTime[nameRecipe] = prepTime
            this.cookingMethod[nameRecipe] = cookingMethod
            this.nutritionalInfo[nameRecipe] = nutritionalInfo
            return "Recipe added successfully"
        } else {
            return "Fill all needed fields"
        }
    }

    fun getRecipe(nameRecipe: String): String {
        if (nameRecipe in recipes) {
            return """
                $nameRecipe
                Ingredients
                ${ingredients[nameRecipe]?.joinToString(", ")}
                Prep Time
                ${prepTime[nameRecipe]}
                Cooking Method
                ${cookingMethod[nameRecipe]}
                Nutritional Information
                ${nutritionalInfo[nameRecipe]}
            """
        } else {
            return "$nameRecipe not found"
        }
    }
}

// **Ancestral Stories:** In many African cultures, the art of storytelling is passed
//down from generation to generation. Imagine you're creating an application that
// records these oral stories and translates them into different languages. The
// stories vary by length, moral lessons, and the age group they are intended for.
//Think about how you would model `Story`, `StoryTeller`, and `Translator`
//objects, and how inheritance might come into play if there are different types of
//stories or storytellers.
data class Stories(var title: String, var genre: String, var length: Int, var moral: String, var age_group: String, var language: String)
open class Story() {
    var stories = mutableListOf<Stories>()
    fun addStory(title: String, genre: String, length: Int, moral: String, age_group: String, language: String) {
        var story = Stories(title, genre, length, moral, age_group, language)
        stories.add(story)
    }

    fun translate(title: String, language: String): String {
        for (a in stories) {
            if (a.title == title) {
                if (a.language == language) {
                    return "the story with title ${a.title} is in ${a.language}"
                } else {
                    return "the story with title ${a.title} is translated"
                }
            }

        }
        return "the story not found"
    }
}
// Create a class called Product with attributes for name, price, and quantity.
// Implement a method to calculate the total value of the product (price * quantity).
// Create multiple objects of the Product class and calculate their total values.
class Product(val name: String, val price: Double, val quantity: Int) {
    fun calculateTotalValue(): Double {
        return price * quantity
    }
}
// Implement a class called Student with attributes for name, age, and grades (a
// list of integers). Include methods to calculate the average grade, display the
// student information, and determine if the student has passed (average grade >=
// 60). Create objects for the Student class and demonstrate the usage of these
// methods.
class Student(val name: String, val age: Int, val grades: List<Int>) {
    fun calculateAverageGrade(): Double {
        val sum = grades.sum()
        return sum.toDouble() / grades.size
    }

    fun displayStudentInformation() {
        println("Name: $name")
        println("Age: $age")
        println("Grades: $grades")
    }

    fun hasPassed(): Boolean {
        val averageGrade = calculateAverageGrade()
        return averageGrade >= 60.0
    }
}
// Create a FlightBooking class that represents a flight booking system. Implement
// methods to search for available flights based on destination and date, reserve
// seats for customers, manage passenger information, and generate booking
// confirmations.
data class Flight(val flightNumber: String, val destination: String, val date: String, var availableSeats: Int)

data class Passenger(val name: String, val age: Int)

class FlightBooking {
    private val flights = mutableListOf<Flight>()
    private val bookings = mutableMapOf<String, MutableList<Passenger>>()

    fun addFlight(flightNumber: String, destination: String, date: String, availableSeats: Int) {
        val flight = Flight(flightNumber, destination, date, availableSeats)
        flights.add(flight)
    }

    fun searchFlights(destination: String, date: String): List<Flight> {
        return flights.filter { it.destination == destination && it.date == date && it.availableSeats > 0 }
    }

    fun reserveSeat(flightNumber: String, passenger: Passenger): Boolean {
        val flight = flights.find { it.flightNumber == flightNumber && it.availableSeats > 0 }
        if (flight != null) {
            val passengerList = bookings.getOrPut(flightNumber) { mutableListOf() }
            passengerList.add(passenger)
            flight.availableSeats--
            return true
        }
        return false
    }

    fun getPassengers(flightNumber: String): List<Passenger> {
        return bookings[flightNumber] ?: emptyList()
    }

    fun generateBookingConfirmation(flightNumber: String): String {
        val flight = flights.find { it.flightNumber == flightNumber }
        val passengers = getPassengers(flightNumber)

        val confirmation = StringBuilder()
        confirmation.append("Flight Booking Confirmation\n")
        confirmation.append("Flight Number: ${flight?.flightNumber}\n")
        confirmation.append("Destination: ${flight?.destination}\n")
        confirmation.append("Date: ${flight?.date}\n")
        confirmation.append("Passengers:\n")

        for (passenger in passengers) {
            confirmation.append("- Name: ${passenger.name}, Age: ${passenger.age}\n")
        }

        return confirmation.toString()
    }
}
// Create a LibraryCatalog class that handles the cataloging and management of
//books in a library. Implement methods to add new books, search for books by
//title or author, keep track of available copies, and display book details.
class Book(val title: String, val author: String, var copies: Int) {
    override fun toString(): String {
        return "$title by $author - $copies available"
    }
}

class LibraryCatalog {
    private val books = mutableListOf<Book>()

    fun addBook(title: String, author: String, copies: Int) {
        val book = Book(title, author, copies)
        books.add(book)
    }

    fun searchByTitle(title: String): List<Book> {
        return books.filter { it.title == title }
    }

    fun searchByAuthor(author: String): List<Book> {
        return books.filter { it.author == author }
    }

    fun displayBookDetails() {
        books.forEach { println(it) }
    }
}



