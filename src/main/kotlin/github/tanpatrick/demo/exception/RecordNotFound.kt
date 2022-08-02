package github.tanpatrick.demo.exception

class RecordNotFound(id: Long) : java.lang.RuntimeException("Record with $id does not exist!")