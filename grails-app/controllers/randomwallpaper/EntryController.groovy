package randomwallpaper



import static org.springframework.http.HttpStatus.*

import javax.naming.SizeLimitExceededException;

import grails.transaction.Transactional

@Transactional(readOnly = true)
class EntryController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def remotePictureService
	
	def mainSave() {
		def mimePicture = ['image/png', 'image/jpeg', 'image/gif']
		def mega = 1024*1024
		def sizeLimit = 1*mega
		def entry = new Entry()
		
		/*if (entry.hasErrors()) {
			render view:'/main'  //TO DO errors....
			return
		}*/
		
		def uploadedFile = request.getFile('picture')
		
		if(!uploadedFile.empty){
			
			if (uploadedFile.size > sizeLimit){
				flash.message = "Picture is to big, ${sizeLimit/mega}MB limit"
				redirect controller: "main"
				return
			}
			entry.mime = uploadedFile.getContentType()
			
			
			
			if (!mimePicture.contains(entry.mime)) {
			  flash.message = "Picture must be one of: ${okcontents}"
			  redirect controller: "main"
			  return
			}

			uploadedFile.transferTo( new File( "web-app/images/pictures/", uploadedFile.originalFilename))
			entry.fileName = uploadedFile.originalFilename
			
			entry.save()
			
			flash.message = "Picture (${entry.fileName}, ${uploadedFile.size} bytes) uploaded."
			redirect controller: "main"
			return
		} else if (params.originalUrl){
			entry.originalUrl = params.originalUrl
			entry = remotePictureService.getPicture(entry)
			if (entry == null){
				log.error "remotePictureService.getPicture(entry) returned null"
				redirect controller: "main"
				return
			}
			entry.save()
			
			flash.message = "Picture ${entry.fileName} uploaded"
			redirect controller: "main"
			return
		}

		flash.message = "Select picture or url"
		redirect controller: "main"
	}
	
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Entry.list(params), model:[entryInstanceCount: Entry.count()]
    }

    def show(Entry entryInstance) {
        respond entryInstance
    }

    def create() {
        respond new Entry(params)
    }

    @Transactional
    def save(Entry entryInstance) {
        if (entryInstance == null) {
            notFound()
            return
        }

        if (entryInstance.hasErrors()) {
            respond entryInstance.errors, view:'create'
            return
        }

        entryInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'entryInstance.label', default: 'Entry'), entryInstance.id])
                redirect entryInstance
            }
            '*' { respond entryInstance, [status: CREATED] }
        }
    }

    def edit(Entry entryInstance) {
        respond entryInstance
    }

    @Transactional
    def update(Entry entryInstance) {
        if (entryInstance == null) {
            notFound()
            return
        }

        if (entryInstance.hasErrors()) {
            respond entryInstance.errors, view:'edit'
            return
        }

        entryInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Entry.label', default: 'Entry'), entryInstance.id])
                redirect entryInstance
            }
            '*'{ respond entryInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Entry entryInstance) {

        if (entryInstance == null) {
            notFound()
            return
        }

        entryInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Entry.label', default: 'Entry'), entryInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'entryInstance.label', default: 'Entry'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
