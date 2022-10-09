import kotlinx.coroutines.*

suspend fun main() {
    CoroutineScope(Dispatchers.Default).launch {
        val re = async {
            100
        }
        re.ca
        val result = withContext(Dispatchers.IO) {
            //resdFile()
            delay(300)
            10
        }
        print("$result")
    }.join()
}