// --- В.1 Метод "Replace Constructor with Factory Method" до рефакторингу

class User(
    val name: String,
    val age: Int
) {
    // ...
}
 
val user = User("Alex", 30)

// --- В.2 Метод "Replace Constructor with Factory Method" після рефакторингу

class User private constructor(
    val name: String
) {
    companion object {
        fun createGuest(): User {
            return User("Guest")
        }
 
        fun createAuthenticated(name: String): User {
            return User(name)
        }
    }
}
 
val guest = User.createGuest()

// --- В.3 Метод "Replace Conditional with Polymorphism" до рефакторингу

enum class AnimalType { DOG, CAT }

class Animal(val type: AnimalType) {
  fun makeSound(): String {
    return when (type) {
      AnimalType.DOG -> "Woof!"
      AnimalType.CAT -> "Meow!"
    }
  }
}


// --- В.4 Метод "Replace Conditional with Polymorphism" після рефакторингу

class Animal {
  abstract fun makeSound(): String
}

class Dog : Animal() {
  override fun makeSound() = "Woof!"
}

class Cat : Animal() {
  override fun makeSound() = "Meow!"
}


// --- В.5 Метод "Separate Domain from Presentation" до рефакторингу

class CartViewModel {
  fun getDisplayTotal(items: List) {
    var total = 0.0
    for (item in items) {
      total += item.price * (1 - item.discount)
    }

    // Відображення:
    textView.text = "Total: ${total}"
  }
}


// --- В.6 Метод "Separate Domain from Presentation" після рефакторингу

class Cart(val items: List) {
  fun calculateTotal(): Double {
    return items.sumOf {
      it.price * (1 - it.discount)
    }
  }
}

class CartViewModel(private val cart: Cart) {
  fun getDisplayTotal(): String {
    val total = cart.calculateTotal()
    return "Total: ${String.format("%.2f", total)}"
  }
}


// --- В.7 Комплексний приклад – до рефакторингу

class Order(
  val type: String,
  val amount: Double
) {
  fun getInvoice(): String {
    val commission = if (type == "VIP") {
      0.0
    } else {
      0.05
    }
    
    val total = amount * (1 + commission)
    
    return "--- INVOICE ---\nTotal: $total USD"
  }
}


// --- В.8 Комплексний приклад – після рефакторингу

class Customer {
  abstract fun getCommission(): Double
  
  class Vip : Customer() { 
    override fun getCommission() = 0.0 
  }
  class Regular : Customer() { 
    override fun getCommission() = 0.05 
  }

 companion object {
    fun create(type: String): Customer {
      return if (type == "VIP") Vip() else Regular()
    }
  }
}

class Order(
  val customer: Customer, 
  val amount: Double
) {
  fun calculateTotal() = amount * (1 + customer.getCommission())
}

class InvoicePresenter {
  fun format(order: Order): String {
    val total = order.calculateTotal()
    return "--- INVOICE ---\nTotal: $total USD"
  }
}
