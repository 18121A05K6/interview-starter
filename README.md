# interview practice starter

A small Java 21 / Spring Boot REST service designed for a 60-minute build-from-scratch interview.

## Prerequisites
- JDK 21
- Maven 3.9+ (or use the Maven integration in your IDE)
- Internet only for `GET /api/users/{id}`; task CRUD works locally without it

## Run
```bash
mvn spring-boot:run
```
The service starts at `http://localhost:8080`.

- `controller`: HTTP mapping only
- `service`: business rules and orchestration
- `repository`: interface + in-memory implementation
- `client`: outbound HTTP integration
- `dto`: validated request model
- `exception`: consistent API error handling


Machine Coding Interview — Candidate Brief
Alert & On-Call Notification Service • 60 minutes • SE / SSE
1. The problem
   You are building the core of a small incident-alerting service, similar in spirit to PagerDuty.
   Monitored services emit alerts. Your service ingests each alert, records it as an incident, and
   — depending on severity — notifies the on-call person. For the most severe alerts, “notify”
   means placing an automated phone call through an external telephony provider (we will use
   a mocked Exotel-style API).
   Build a small HTTP Service around this.
   1.1 Severity → notification rules
   • CRITICAL → place a phone call via the Exotel mock to the on-call number for that
   service, with the alert message as the spoken text.
   • WARNING → no phone call. Record the incident with notification status SKIPPED
   (reason: severity below threshold).
   • INFO → record the incident only. No notification.
   1.2 Seed / configuration data (you may hardcode this)
   A static map of serviceId → on-call phone number. You do not need an API to manage this.
   {
   "svc-payments": "+919900112233",
   "svc-checkout": "+919900445566",
   "svc-search": "+919900778899"
   }
   1.3 APIs to implement
   (a) Ingest an alert
   POST /alert — accepts an alert, creates an incident, applies the notification rules above,
   and returns the created incident.
   Request body:
   {
   "serviceId": "svc-payments",
   "severity": "CRITICAL", // INFO | WARNING | CRITICAL
   "title": "Payment gateway timeout",
   "description": "p99 latency > 5s for 3 minutes",
   "dedupKey": "payments-latency-001",---- //dedupkey:incident ----
   "timestamp": "2026-06-09T10:15:30Z"
   }
    // 10000--sequ
    // 3 retries---  active --failuredDB, alerting // 30mins--
    //downstream --- circuit breaker ---

//synchro--400-600ms -- REST api -- validation--returning ,--asynchron phone call
//  incidient --kafka() ---process it --incdient notif
// 

   (see incident shape in 1.4)
 //Response
   { "incidentId":,  triggerStatus:"SKIPPED/NOTIFIED"(enum)
 }
   (b) List incidents
   GET /incidents?status=TRIGGERED — returns all incidents, optionally filtered by status.
   1.4 Incident shape (what you persist and return)
   {
   "incidentId": "inc-1029",
   "serviceId": "svc-payments",
   "severity": "CRITICAL",
   "title": "Payment gateway timeout",
   "description": "p99 latency > 5s for 3 minutes",
   Candidate Brief — share with candidate • Page 2
   "status": "NOTIFIED", // TRIGGERED | NOTIFIED | RESOLVED
   "createdAt": "2026-06-09T10:15:31Z",
   "notification": {
   "channel": "PHONE",
   "status": "SENT", // SENT | FAILED | SKIPPED
   "providerCallId": "CA-7781", // from the provider on success, else null
   "lastError": null // short reason when FAILED
   }
   }
   1.5 The Exotel mock (external phone-call API)
   Request
   POST {EXOTEL_BASE_URL}/v1/calls
   {
   "to": "+919900112233",
   "message": "CRITICAL: Payment gateway timeout on svc-payments"
   }
   Response
   Success — HTTP 200:
   {
   "callSid": "CA-7781",
   "status": "queued"
   }
2. Scope — start here, then go as far as you can
   Core (aim to get this working and clean):
   • POST /alert ingesting an alert and creating an incident with the correct notification
   behaviour per severity.
   • The CRITICAL path calling the Exotel mock and recording the outcome (SENT /
   FAILED) on the incident.
   • List Incidents API
   • Sensible models, layering, logging, and error handling throughout