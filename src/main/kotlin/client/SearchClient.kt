package client

import api.NetworkManager
import api.entity.ResponseEntity
import errorhandler.ApiCallException

import java.awt.Point


class SearchClient (private val network: NetworkManager) {

    fun runSearch(): Point{
        val responseEntity: ResponseEntity = network.requestDirections()

        return responseEntity.run {

            if(serviceError!= null) throw ApiCallException(serviceError)

            calculateLocation(directions!!)
        }
    }

    private fun calculateLocation(directions: List<String>): Point{

        // Starting at point  x = 0, y = 0
        val location: Point = Point(0, 0)

        //Start by facing North
        //# 0 = north, 1 = east, 2 = south, 3 = west
        var facing = 0;

        val moves = arrayOf(
            arrayOf(0, 1),
            arrayOf(1, 0),
            arrayOf(0, -1),
            arrayOf(-1, 0)
        )

        for (direction in directions){
            /**
             * We have a range of 4 possible directions so we enforce this with modulo 4
             * turning left means we will be facing the same direction
             * if we turn right 3 times.
             */

            when (direction) {
                "left" -> facing = (facing + 3) % 4
                "right" -> facing = (facing + 1) % 4
                //go forward in the facing direction
                "forward" -> {
                    location.x +=  moves[facing][0]
                    location.y +=  moves[facing][1]
                }
            }
        }
        return location
    }

    fun checkLocation(location: Point): String{
        val responseEntity: ResponseEntity = network.requestLocationCheck(location)

        return responseEntity.run {

            if(serviceError!= null) throw ApiCallException(serviceError)

            message!!
        }
    }

}