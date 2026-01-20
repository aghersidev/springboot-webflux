import time,random,requests
from datetime import datetime,timezone

SPRING_HEALTH="http://localhost:8080/actuator/health"
SALES_ENDPOINT="http://localhost:8080/sales"

EVENTS_PER_SECOND=5
TIME_SCALE=1.0

COUNTRIES=["UK","FR","DE","ES","PE","US"]
STOCKS=["A1","B2","C3","D4","E5"]

def wait_for_spring():
    while True:
        try:
            if requests.get(SPRING_HEALTH,timeout=1).status_code==200:
                print("Spring Boot is up")
                return
        except:
            pass
        time.sleep(1)

def random_sale(ts):
    qty=random.randint(1,10)
    price=round(random.uniform(1.0,50.0),2)
    return {
        "invoiceNo":f"INV-{random.randint(1,10_000_000)}",
        "stockCode":random.choice(STOCKS),
        "quantity":qty,
        "unitPrice":price,
        "customerId":f"CUST-{random.randint(1,100_000)}",
        "country":random.choice(COUNTRIES),
        "timestamp":ts.isoformat()
    }

def replay():
    while True:
        start=time.time()
        ts=datetime.now(timezone.utc)

        for _ in range(EVENTS_PER_SECOND):
            payload=random_sale(ts)
            r=requests.post(SALES_ENDPOINT,json=payload)
            if r.status_code!=200:
                print("Error",r.status_code,r.text)

        elapsed=time.time()-start
        sleep=max(0,(1.0/TIME_SCALE)-elapsed)
        time.sleep(sleep)

if __name__=="__main__":
    wait_for_spring()
    replay()
