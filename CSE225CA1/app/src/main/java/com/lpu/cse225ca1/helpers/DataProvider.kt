package com.lpu.cse225ca1.helpers

import com.lpu.cse225ca1.models.CardItem

// CLASS FOR PROVIDING DUMMY DATA FOR UI
class DataProvider {
    // LIST OF IMAGE URLS
    private val imagesList: List<String> = listOf(
        "https://i.picsum.photos/id/798/600/300.jpg?hmac=xJ8DMxpneYcZ9cDSkDjzppiHJqEolqmfC6ZlvYE5fCA",
        "https://i.picsum.photos/id/635/600/300.jpg?hmac=hfA0z6CrW8uYRWqDiEy16vvdqq2cBPn6HuAeIRFjcWw",
        "https://i.picsum.photos/id/841/600/300.jpg?hmac=TI8OPtioZBGTxYQgc2QQzGsDPP8LxgTY2fuMutA-L5g",
        "https://i.picsum.photos/id/743/600/300.jpg?hmac=5xth2PCDy00D5Mgf54SaLAePVL4-YUcB_mU6EGWey8k",
        "https://i.picsum.photos/id/782/600/300.jpg?hmac=Dt1I8JCtBEQS9ZJiJrBZiVnlNjmlp1pbYoUuHCWWfiM",
        "https://i.picsum.photos/id/236/600/300.jpg?hmac=z2J8Ss6_tmNC7L9GAb2hAPMpWLmpveBuRVPmDSh04qs",
        "https://i.picsum.photos/id/76/600/300.jpg?hmac=d1znDjepQTE3k4ZL8OvogtPL-K7PTAs6ZfhPN3AxQGY",
        "https://i.picsum.photos/id/938/600/300.jpg?hmac=2Tvkeeu4jNOPXRcQggvcOF6jVMcxv5KErQeI3NaBqsM",
        "https://i.picsum.photos/id/249/600/300.jpg?hmac=VdkbvsMB-yhCykwdeeSKahGfqA30E0k24_oUdsKbsCA",
        "https://i.picsum.photos/id/610/600/300.jpg?hmac=TxW3Zp6huiZVE4vYF9PHOM9htZPSVsaUAtElnJ_eDgc"
    )

    fun getRandomCardItemList(size: Int = 10): ArrayList<CardItem> {
        val cardsList: ArrayList<CardItem> = ArrayList()
        for (idx in 1..size) {
            cardsList.add(
                CardItem(
                    id = idx,
                    title = "Landscape",
                    imageUrl = imagesList.random()
                )
            )
        }
        return cardsList
    }
}