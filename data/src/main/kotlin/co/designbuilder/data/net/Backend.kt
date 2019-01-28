package co.designbuilder.data.net

import android.content.Context
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection.HTTP_NOT_FOUND
import java.net.HttpURLConnection.HTTP_OK

class Backend private constructor() {

  private val server: MockWebServer = MockWebServer()

  @Volatile
  lateinit var baseUrl: String
    private set

  fun start(appContext: Context) {
    server.setDispatcher(AssetFileDispatcher(appContext))

    Thread(Runnable {
      try {
        server.start()
        baseUrl = server.url("/").toString()
      } catch (e: IOException) {
        e.printStackTrace()
      }
    }).start()
  }

  private inner class AssetFileDispatcher(private val context: Context) : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {

      val assetFilename = request.path.replace("/", "") + ".json"

      return try {
        val stream = context.assets.open(assetFilename)

        val json = parseStream(stream)

        MockResponse()
            .setBody(json)
            .setResponseCode(HTTP_OK)

      } catch (e: IOException) {
        MockResponse()
            .setResponseCode(HTTP_NOT_FOUND)
      }

    }

    private fun parseStream(stream: InputStream): String {
      val bufferedReader = BufferedReader(InputStreamReader(stream, "UTF-8"))
      return bufferedReader.use(BufferedReader::readText)
    }
  }

  companion object {

    val instance = Backend()
  }
}
