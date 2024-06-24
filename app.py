import re
import subprocess

from flask import Flask, jsonify
from flask_restful import Resource, Api

app = Flask(__name__)
api = Api(app)


class UpsStatusApi(Resource):
    def get(self):
        status = (subprocess.Popen(['sudo', 'pwrstat', '-status'], stdout=subprocess.PIPE).communicate()[0]
                  .decode('utf-8'))
        response = {'model_name': re.search('Model Name\\.* (.*)', status).group(1),
                    'firmware_number': re.search('Firmware Number\\.* (.*)', status).group(1),
                    'rating_voltage': int(re.search('Rating Voltage\\.* ([0-9]*)', status).group(1)),
                    'rating_power_in_watts': int(re.search('Rating Power\\.* ([0-9]*)', status).group(1)),
                    'state': re.search('State\\.* (.*)', status).group(1),
                    'power_supply_by': re.search('Power Supply by\\.* (.*)', status).group(1),
                    'utility_voltage': int(re.search('Utility Voltage\\.* ([0-9]*)', status).group(1)),
                    'output_voltage': int(re.search('Output Voltage\\.* ([0-9]*)', status).group(1)),
                    'battery_capacity': int(re.search('Battery Capacity\\.* ([0-9]*)', status).group(1)),
                    'remaining_runtime_in_minutes': int(re.search('Remaining Runtime\\.* ([0-9]*)', status).group(1)),
                    'load_in_watts': int(re.search('Load\\.* ([0-9]*) Watt', status).group(1)),
                    'load_n_percentage': int(re.search('Load\\.* [0-9]* Watt\\(([0-9]*) %\\)', status).group(1)),
                    'line_interaction': re.search('Line Interaction\\.* (.*)', status).group(1),
                    'test_result': re.search('Test Result\\.* (.*)', status).group(1),
                    'last_power_event': re.search('Last Power Event\\.* (.*)', status).group(1)}

        return jsonify(response)


api.add_resource(UpsStatusApi, '/ups/status')

if __name__ == '__main__':
    app.run(port=9090, host='0.0.0.0')
