import psycopg2
from faker import Faker


if __name__ == "__main__":

    n = 1000 * 1000
    fake = Faker()

    try:
        connection = psycopg2.connect(
                                  user="db_user",
                                  password="db_password",
                                  host="localhost",
                                  port="",
                                  database="db_recordlabel")

        cursor = connection.cursor()
        postgres_insert_query = """ INSERT INTO "RecordLabel_app_eventfounder" (name, rating, email, phone) VALUES (%s,%s,%s,%s)"""
        
        for _ in range(n):
            name = fake.name()
            rating = 1
            email = fake.email()
            phone = fake.msisdn() 
            record_to_insert = (name, rating, email, phone)
            cursor.execute(postgres_insert_query, record_to_insert)

        connection.commit()

        print(n, "Record inserted successfully into mobile table")

    except (Exception, psycopg2.Error) as error:
        print("Failed to insert record into mobile table", error)

    finally:
    # closing database connection.
        if connection:
            cursor.close()
            connection.close()
            print("PostgreSQL connection is closed")    
