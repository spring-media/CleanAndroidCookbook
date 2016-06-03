package pro.averin.anton.clean.android.cookbook.data.flickr.model

import org.parceler.Parcel

data class FlickrResponse(
        var page: Int = 0,
        var pages: Int = 0,
        var perPage: Int = 0,
        var total: Int = 0,
        var photo: List<Photo> = emptyList()
)

@Parcel(Parcel.Serialization.BEAN) data class Photo(
        var id: String = "",
        var owner: String = "",
        var secret: String = "",
        var server: String = "",
        var farm: Int = 0,
        var title: String = "",
        var ispublic: Boolean = false,
        var isfriend: Boolean = false,
        var isfamily: Boolean = false,
        var descrption: PhotoDescription = PhotoDescription(),
        var dateupload: Long = 0,
        var datetaken: String = "",
        var datetakengranularity: String = "",
        var datetakeunknown: String = "",
        var ownername: String = "",
        var tags: String = "",
        var latitude: Double = 0.0,
        val longitude: Double = 0.0,
        var accuracy: Int = 0,
        var context: Int = 0,
        var media: String,
        var media_status: String
)

@Parcel(Parcel.Serialization.BEAN) data class PhotoDescription(
        var _content: String = ""
)

class Method {
    companion object {
        const val SEARCH = "flickr.photos.search"
    }
}

class Sort {
    companion object {
        const val INTREST_DESC = "interestingness-desc"
    }
}

class Media {
    companion object {
        const val PHOTOS = "photos"
    }
}
