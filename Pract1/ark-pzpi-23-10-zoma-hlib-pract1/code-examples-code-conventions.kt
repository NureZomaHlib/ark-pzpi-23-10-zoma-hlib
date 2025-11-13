//=======================================
// В.1 Поганий і хороший стиль коду
//=======================================

fun f(a:Int,b:Int)=a+b //поганий, незрозуміла назва, немає отступів

fun calculateSum(a: Int, b: Int): Int { //хороший
    return a + b
} 

//=======================================
// В.2 Зрозумілі імена змінних, функцій, класів
//=======================================

//Поганий
var x = "Ivan" // незрозуміле ім’я
fun f(a: Int, b: Int) = a + b // невідомо, що
робить функція
class u { // беззмістовна назва класу
var n: String = "" //незрозуміле ім’я
}
const val m = 100 //незрозуміла константа

//Хороший
val userName = "Ivan"
fun calculateSum(a: Int, b: Int) = a + b
class User(val name: String)
const val MAX_USERS = 100

//=======================================
// В.3 Іменування
//=======================================

//Поганий
var x = "Ivan" // незрозуміле ім’я
fun f(a: Int, b: Int) = a + b // невідомо, що робить функція

class u { // беззмістовна назва класу
    var n: String = "" //незрозуміле ім’я
}

const val m = 100 //незрозуміла константа

//Хороший
val userName = "Ivan"

fun calculateSum(a: Int, b: Int) = a + b

class User(val name: String)

const val MAX_USERS = 100

//=======================================
// В.4 Коментарі та документація
//=======================================

//Поганий
// функція для додавання
fun sum(a: Int, b: Int): Int {
    //додаємо два числа
    return a + b;
}

//var x = 10 // стара змінна

//Хороший 
/**
 * Обчислює ссуму двох чисел
 * @param a перше число
 * @param b друге число
 * @return результат додавання
 */
fun sum(a: Int, b: Int): Int{
    return a + b;
}

//=======================================
// В.5 Використання типів і Null-safety
//=======================================

//Без форматування
class Foo(
        val id : String, val age : Int,
        val name:String )
        {
            fun foo() : Boolean { return name.isEmpty()
        }
        }



//З форматуванням
class Foo(
    val id: String,
    val age: Int,
    val name: String
) {
    fun foo(): Boolean {
        return name.isEmpty()
    }
}

//=======================================
// В.6 Функції та лямбди в Kotlin
//=======================================

//Поганий 
fun process(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (i in list) { // зайвий цикл
        if (i % 2 == 0) {
            result.add(i * 2) //можна зробити через map
        }
    }
    return result
}

//Хороший 
fun process(list: List<Int>): List<Int> =
    list.filter { it % 2 == 0 }
        .map { it * 2 }

//=======================================
// В.7 Рефакторинг та підтримка коду
//=======================================

//Поганий
fun doEverything() {
    // функція робить все одразу, важко читати
    val a = 10
    val b = 20
    val c = a + b
    println(c)
    // багато інших дій тут
}


//Хороший
fun calculateSum(a: Int, b: Int) = a + b // функція робить одну дію

fun printSum(a: Int, b: Int) { // розділено на логічні блоки
    val sum = calculateSum(a, b)
    println(sum)
}

//=======================================
// В.8 Обробка помилок у Kotlin
//=======================================

//Поганий
val input: String? = null
println(input!!.length) // аварійне розіменування, може покласти програму

try {
    val x = 10 / 0
} catch (e: Exception) {
    // порожній catch, помилка загубиться
}

//Хороший
val input: String? = null
val length = input?.length ?: 0 // безпечне розіменування

try {
    val x = 10 / 0
} catch (e: ArithmeticException) {
    println("Ділення на нуль: ${e.message}") // зрозуміле повідомлення
}

//=======================================
// В.9 Практичні поради для чистого коду
//=======================================

//Поганий
fun calculate() {
    // дублювання коду в різних місцях
    val a = 10 + 20
    val b = 10 + 20
    val c = 10 + 20
    println(a + b + c)
}

//Хороший
fun sum(a: Int, b: Int) = a + b // функція повторно використовується
fun calculate() {
    val x = sum(10, 20)
    val y = sum(10, 20)
    val z = sum(10, 20)
    println(x + y + z) // код без дублювань

}
