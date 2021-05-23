package api

import com.nhaarman.mockitokotlin2.whenever
import com.nhaarman.mockitokotlin2.mock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import retrofit2.Call
import retrofit2.Response

import api.entity.ResponseEntity
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import errorhandler.entity.ServiceError
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import java.awt.Point


class NetworkManagerTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var apiServe: ApiService

    private lateinit var networkManager: NetworkManager

    @Before
    fun setUp() {
        networkManager = NetworkManager(apiServe)
    }

    @Test
    fun requestDirectionsSuccessful() {

        val data = ResponseEntity(directions = arrayListOf("left","forward","right"))

        val serviceCall = mock<Call<ResponseEntity>>()
        val response = mock<Response<ResponseEntity>>()

        whenever(apiServe.getDirections(any())).thenReturn(serviceCall)
        whenever(serviceCall.execute()).thenReturn(response)
        whenever(response.isSuccessful).thenReturn(true)
        whenever(response.body()).thenReturn(data)

        val results = networkManager.requestDirections()
        verify(apiServe).getDirections(any())

        assertEquals(data,results)
    }

    @Test
    fun requestDirectionsNotSuccessful() {

        val serviceError = ServiceError(code = 404, title = "Request Directions Test Error", message = null)
        val data = ResponseEntity(serviceError = serviceError)

        val serviceCall = mock<Call<ResponseEntity>>()
        val response = mock<Response<ResponseEntity>>()
        val okhttpResponse = mock<okhttp3.Response>()
        val responseBody = mock<ResponseBody>()

        whenever(apiServe.getDirections(any())).thenReturn(serviceCall)
        whenever(serviceCall.execute()).thenReturn(response)
        whenever(response.raw()).thenReturn(okhttpResponse)
        whenever(response.errorBody()).thenReturn(responseBody)

        whenever(response.isSuccessful).thenReturn(false)
        whenever(response.body()).thenReturn(data)

        whenever(response.raw().code).thenReturn(404)
        whenever(response.raw().message).thenReturn("Request Directions Test Error")

        val results = networkManager.requestDirections()
        verify(apiServe).getDirections(any())

        assertEquals(data,results)
    }

    @Test
    fun requestLocationCheckSuccessful() {

        val data = ResponseEntity(message = "Location Checked")
        val point = Point(0,0)

        val serviceCall = mock<Call<ResponseEntity>>()
        val response = mock<Response<ResponseEntity>>()

        whenever(apiServe.checkLocation(any(),any(),any())).thenReturn(serviceCall)
        whenever(serviceCall.execute()).thenReturn(response)
        whenever(response.isSuccessful).thenReturn(true)
        whenever(response.body()).thenReturn(data)

        val results = networkManager.requestLocationCheck(point)
        verify(apiServe).checkLocation(any(), any(), any())

        assertEquals(data,results)
    }

    @Test
    fun requestLocationCheckNotSuccessful() {

        val serviceError = ServiceError(code = 404, title = "Request Location Check Test Error", message = null)
        val data = ResponseEntity(serviceError = serviceError)
        val point = Point(0,0)

        val serviceCall = mock<Call<ResponseEntity>>()
        val response = mock<Response<ResponseEntity>>()
        val okhttpResponse = mock<okhttp3.Response>()
        val responseBody = mock<ResponseBody>()

        whenever(apiServe.checkLocation(any(),any(),any())).thenReturn(serviceCall)
        whenever(serviceCall.execute()).thenReturn(response)
        whenever(response.raw()).thenReturn(okhttpResponse)
        whenever(response.errorBody()).thenReturn(responseBody)

        whenever(response.isSuccessful).thenReturn(false)
        whenever(response.body()).thenReturn(data)

        whenever(response.raw().code).thenReturn(404)
        whenever(response.raw().message).thenReturn("Request Location Check Test Error")

        val results = networkManager.requestLocationCheck(point)
        verify(apiServe).checkLocation(any(), any(), any())

        assertEquals(data,results)
    }

}