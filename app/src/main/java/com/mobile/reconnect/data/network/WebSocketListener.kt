import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class WebSocketListener : WebSocketListener() {

	override fun onOpen(webSocket: WebSocket, response: okhttp3.Response) {
		println("WebSocket Connected")
		// 메시지 보내기 예시
		webSocket.send("Hello from client!")
	}

	override fun onMessage(webSocket: WebSocket, text: String) {
		println("Received text message: $text")
	}

	override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
		println("Received binary message: ${bytes.hex()} ")
	}

	override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
		println("Closing WebSocket: Code $code, Reason: $reason")
		webSocket.close(1000, null)
		println("WebSocket Closed")
	}

	override fun onFailure(webSocket: WebSocket, t: Throwable, response: okhttp3.Response?) {
		println("WebSocket Error: ${t.message}")
	}

}
