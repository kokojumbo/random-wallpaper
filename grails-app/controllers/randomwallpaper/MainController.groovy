package randomwallpaper

class MainController {

	def remotePictureService
	def test() {
		def entry = new Entry()
		entry.originalUrl = "http://p3.sfora.pl/d9295fcebe349671ce64c35e5bfe2b8f.jpg"
		entry = remotePictureService.getPicture(entry)
		
		if (entry == null){
			log.error "remotePictureService.getPicture(entry) returned null"
			redirect controller: "main"
			return
		}
		entry.save()
		flash.message = "created"
		redirect controller: "main"
		
	}
	
    def index() {
		def select = Entry.get(params.select ?: 1)
		respond Entry.list(params), view: "/main", model: [entryInstanceCount: Entry.count(), select: select]
	}
}
