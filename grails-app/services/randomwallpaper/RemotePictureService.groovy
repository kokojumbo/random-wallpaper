package randomwallpaper

import grails.transaction.Transactional

@Transactional
class RemotePictureService {

	def getPicture(Entry entry) {
		//TODO error checking

		def mimePicture = [
			'image/png',
			'image/jpeg',
			'image/gif'
		]
		entry.mime = "image/jpeg"	//TODO

		def url
		try{
			url = new URL(entry.originalUrl)
		}catch(all){
			log.error "bad url"
			return null
		}

		entry.fileName = entry.originalUrl.tokenize("/")[-1]

		
		def out = new File('web-app/images/pictures/' + entry.fileName).newOutputStream()
		out << url.openStream()
		out.close()
		return entry

	}
}



/*
 withRest(uri: url.toURI()) {	//HEAD request
 def response = head(path: url.getPath())
 println "head headers:"
 response.headers.each { println "${it.name} : ${it.value}" }
 def mime = response.headers.'Content-Type'
 }*/
