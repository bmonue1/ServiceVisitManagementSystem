from model.service_visit import ServiceVisit

class ServiceVisitManager:
    def __init__(self):
        self._activeVisits = {}
        self._loadVisits()
        
    def _loadVisits(self):
        visit1 = ServiceVisit("George", "3 Abbey Road, London NW8 9AY, England", "install service");
        visit2 = ServiceVisit("Paul", "3 Abbey Road, London NW8 9AY, England", "install service");
        visit3 = ServiceVisit("John", "3 Abbey Road, London NW8 9AY, England", "install service");
        visit4 = ServiceVisit("Ringo", "3 Abbey Road, London NW8 9AY, England", "install service");
        self._activeVisits[visit1.getVisitId()] = visit1
        self._activeVisits[visit2.getVisitId()] = visit2
        self._activeVisits[visit3.getVisitId()] = visit3
        self._activeVisits[visit4.getVisitId()] = visit4
    
    def createServiceVisit(self, customerName, customerAddress, description):
        status = ""
        serviceId = -1
        message = ""
        try:
            visit = ServiceVisit(customerName, customerAddress, description)
        except ValueError as err:
            status = "fail"
            message = "%s".format(err)
        else:
            self._activeVisits[visit.getVisitId()] = visit
            status = "success"
            serviceId = visit.getVisitId()
        finally:
            return (status, serviceId, message)
