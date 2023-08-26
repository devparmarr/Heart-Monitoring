import serial
import MySQLdb as mdb
import time

serialPort = serial.Serial("COM5", 9600, timeout=0.5)
def get():
    db = mdb.connect('182.50.133.80', \
                          'mayuriwagh', \
                          'T5j@h6k9', \
                          'academyprojectmw' );
    cursor = db.cursor()
    sql = "SELECT pid FROM `tblsessionmaster`"
    cursor.execute(sql)
    un = cursor.fetchall()[0]
    #un1 = cursor.fetchall()[1]
    db.close()
    un=str(un[0])
    #un1=str(un[1])
    #print un
    return un

def push1(heart_rate,ecg,oxy_saturation,temp_sensor,pid):            
            con = mdb.connect('182.50.133.80', \
                          'mayuriwagh', \
                          'T5j@h6k9', \
                          'academyprojectmw' );
            cur = con.cursor()
            cur.execute("TRUNCATE TABLE `sens1`")
            cur.execute("""INSERT INTO sensor_data(heart_rate,ECG_data,oxy_saturation,temp_sensor,pid) \
                       VALUES(%s,%s,%s,%s,%s)""", (heart_rate,ecg,oxy_saturation,temp_sensor,pid))            
            con.commit()
heart_rate=0
oxy_saturation=0
ecg=0

temp=0
sys=0
dia=0
pr=0
qt=0
pid=0
while True:
    #pid=get()
    str1=serialPort.readline()
    #print(str1)
    if str1!='':
            if str1[0]=='A':
                heart_rate,oxy_saturation,ecg,temp=str1.split(',')
                temp=float(temp)
                heart_rate=heart_rate[1:]
                heart_rate=float(heart_rate)
                ecg=float(ecg)
                print(heart_rate,oxy_saturation,ecg,temp,pid)
                push1(heart_rate,ecg,oxy_saturation,temp,pid)
            
