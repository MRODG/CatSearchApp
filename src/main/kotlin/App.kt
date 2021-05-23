import api.NetworkManager
import client.SearchClient
import di.appModule
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class App : KoinComponent {

    val searchClient by inject<SearchClient>()
    companion object {
        val logger: Logger = LoggerFactory.getLogger(App::class.java)
    }
}

fun main() {
    App.logger.info("===============================")
    App.logger.info("Starting up Cat Search Client")
    App.logger.info("===============================")
    startKoin {
        // declare di modules
        modules(appModule)
    }
    val app = App()
    val location = app.searchClient.runSearch()
    App.logger.info("Checking Location: x = " + location.x + " y = " + location.y)
    val outcome = app.searchClient.checkLocation(location)

    App.logger.info(outcome)
}