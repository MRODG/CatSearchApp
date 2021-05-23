package api

import api.entity.ResponseEntity
import errorhandler.entity.ServiceError
import okhttp3.ResponseBody
import java.awt.Point

class NetworkManager(private val apiServe: ApiService) {

    fun requestDirections(): ResponseEntity {
        apiServe.getDirections(NetworkManager.DEV_EMAIL).execute().run {
            return if (this.isSuccessful){
                this.body()!!
            } else{
                val serviceError = ServiceError(code = this.raw().code, title = this.raw().message, message = (this.errorBody() as ResponseBody).string())
                ResponseEntity(serviceError = serviceError)
            }
        }
    }

    fun requestLocationCheck(point: Point): ResponseEntity {
        apiServe.checkLocation(NetworkManager.DEV_EMAIL, point.x,point.y).execute().run {
            return if (this.isSuccessful){
                this.body()!!
            }  else{
                val serviceError = ServiceError(code = this.raw().code, title = this.raw().message, message = (this.errorBody() as ResponseBody).string())
                ResponseEntity(serviceError = serviceError)
            }
        }
    }

    companion object {
        val BASE_URL = "https://which-technical-exercise.herokuapp.com/"
        val DEV_EMAIL = "marios.odigie93@gmmail.com/"
    }
}
