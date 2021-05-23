package client

import api.NetworkManager
import api.entity.ResponseEntity
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import errorhandler.ApiCallException
import errorhandler.entity.ServiceError
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import java.awt.Point


class SearchClientTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var networkManager: NetworkManager

    private lateinit var searchClient: SearchClient

    @Before
    fun setUp() {
        searchClient = SearchClient(networkManager)
    }

    @Test
    fun runSearchSuccessful() {

        val data = ResponseEntity(directions = arrayListOf("left","forward","right","forward"))
        val point = Point(-1,1)

        whenever(networkManager.requestDirections()).thenReturn(data)

        val results = searchClient.runSearch()

        verify(networkManager).requestDirections()
        assertEquals(point, results)
    }

    @Test(expected = ApiCallException::class)
    @Throws(ApiCallException::class)
    fun runSearchFails()  {

        val serviceError = ServiceError(code = 404, title = "Run Search failed", message = null)
        val data = ResponseEntity(serviceError = serviceError)

        whenever(networkManager.requestDirections()).thenReturn(data)

        searchClient.runSearch()
    }
    @Test
    fun checkLocationSuccessful() {

        val data = ResponseEntity(message = "Location has been checked")
        val point = Point(10,10)

        whenever(networkManager.requestLocationCheck(any())).thenReturn(data)

        val results = searchClient.checkLocation(point)

        verify(networkManager).requestLocationCheck(any())
        assertEquals("Location has been checked", results)
    }

}