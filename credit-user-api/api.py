from flask import Flask, request, Response, jsonify
from dataclasses import dataclass
import os

app = Flask(__name__)


@dataclass
class CreditUserEntity:
    ID = "id"
    PID = "pid"
    NAME = "name"
    SURNAME = "surname"
    AGE = "age"
    COUNTRY = "country"
    ADDRESS = "address"


credit_users = {
    '7VJ3X921': {
        CreditUserEntity.ID: 'b7c931a9-6d46-4dba-8a89-c3bed7d3b3ea',
        CreditUserEntity.PID: '7VJ3X921',
        CreditUserEntity.NAME: 'Betty',
        CreditUserEntity.SURNAME: 'Cox',
        CreditUserEntity.COUNTRY: 'Greece',
        CreditUserEntity.AGE: 31,
        CreditUserEntity.ADDRESS: '74444 St. Charles Avenue'
    },
    '5LF9K788': {
        CreditUserEntity.ID: 'a0f9f9a1-d8f8-4e81-a86b-e47f5eac8c50',
        CreditUserEntity.PID: '5LF9K788',
        CreditUserEntity.NAME: 'Ann',
        CreditUserEntity.SURNAME: 'Jacobs',
        CreditUserEntity.COUNTRY: 'Spain',
        CreditUserEntity.AGE: 54,
        CreditUserEntity.ADDRESS: '71955 St. Charles Street'
    },
    '2KV1E078': {
        CreditUserEntity.ID: 'e5b1d9d7-d3c3-4f5a-b4c4-b4ebe8cce1f2',
        CreditUserEntity.PID: '2KV1E078',
        CreditUserEntity.NAME: 'Mildred',
        CreditUserEntity.SURNAME: 'Jackson',
        CreditUserEntity.COUNTRY: 'Argentina',
        CreditUserEntity.AGE: 56,
        CreditUserEntity.ADDRESS: '83636 St. Charles Avenue'
    },
    '9VV9H937': {
        CreditUserEntity.ID: '6d9a6dca-0f86-46c2-b98e-cd87c8f7b731',
        CreditUserEntity.PID: '9VV9H937',
        CreditUserEntity.NAME: 'Lillian',
        CreditUserEntity.SURNAME: 'Hudson',
        CreditUserEntity.COUNTRY: 'France',
        CreditUserEntity.AGE: 43,
        CreditUserEntity.ADDRESS: '21525 St. Charles Street'
    },
    '8WJ9K933': {
        CreditUserEntity.ID: '2f7c4bb4-0f7a-4c29-8e90-e1f0f7fcedcd',
        CreditUserEntity.PID: '8WJ9K933',
        CreditUserEntity.NAME: 'Jack',
        CreditUserEntity.SURNAME: 'Black',
        CreditUserEntity.COUNTRY: 'Ireland',
        CreditUserEntity.AGE: 47,
        CreditUserEntity.ADDRESS: '34487 St. Charles Street'
    },
    '3PP7G194': {
        CreditUserEntity.ID: 'a5f81722-f9f9-44e1-881a-a7a1a1f7e428',
        CreditUserEntity.PID: '3PP7G194',
        CreditUserEntity.NAME: 'Shirley',
        CreditUserEntity.SURNAME: 'Johnston',
        CreditUserEntity.COUNTRY: 'Mexico',
        CreditUserEntity.AGE: 42,
        CreditUserEntity.ADDRESS: '82888 St. Charles Street'
    },
    '7ZM1A081': {
        CreditUserEntity.ID: 'c81b0ce8-9f9c-4444-938f-a355b834ab05',
        CreditUserEntity.PID: '7ZM1A081',
        CreditUserEntity.NAME: 'Steven',
        CreditUserEntity.SURNAME: 'Weaver',
        CreditUserEntity.COUNTRY: 'Colombia',
        CreditUserEntity.AGE: 39,
        CreditUserEntity.ADDRESS: '45021 St. Charles Street'
    },
    '3LF3T099': {
        CreditUserEntity.ID: '7c48e3b3-7d2e-4ce4-a4d6-d4e8b6c475cb',
        CreditUserEntity.PID: '3LF3T099',
        CreditUserEntity.NAME: 'Lois',
        CreditUserEntity.SURNAME: 'Perez',
        CreditUserEntity.COUNTRY: 'Peru',
        CreditUserEntity.AGE: 61,
        CreditUserEntity.ADDRESS: '82771 St. Charles Street'
    },
    '2GG2H817': {
        CreditUserEntity.ID: '9f65f6d8-0baf-4b1f-b3c3-f8d7cde3f944',
        CreditUserEntity.PID: '2GG2H817',
        CreditUserEntity.NAME: 'Johnny',
        CreditUserEntity.SURNAME: 'Gonzales',
        CreditUserEntity.COUNTRY: 'Chile',
        CreditUserEntity.AGE: 45,
        CreditUserEntity.ADDRESS: '90609 St. Charles Street'
    },
    '4XV4H261': {
        CreditUserEntity.ID: '9f4b4a8a-12ae-4ec2-b5a5-8db3815f78cc',
        CreditUserEntity.PID: '4XV4H261',
        CreditUserEntity.NAME: 'Jimmy',
        CreditUserEntity.SURNAME: 'White',
        CreditUserEntity.COUNTRY: 'Brazil',
        CreditUserEntity.AGE: 44,
        CreditUserEntity.ADDRESS: '18063 St. Charles Street'
    },
    '1XR9T816': {
        CreditUserEntity.ID: '5b5a9e1d-2d0b-4a3c-b7e1-cb2d8477d51c',
        CreditUserEntity.PID: '1XR9T816',
        CreditUserEntity.NAME: 'Willie',
        CreditUserEntity.SURNAME: 'Lopez',
        CreditUserEntity.COUNTRY: 'Indonesia',
        CreditUserEntity.AGE: 58,
        CreditUserEntity.ADDRESS: '81617 St. Charles Avenue'
    },
    '4LC4A831': {
        CreditUserEntity.ID: '9dcfeb64-8f7a-4a62-ad78-8dacf0c45d9f',
        CreditUserEntity.PID: '4LC4A831',
        CreditUserEntity.NAME: 'Cheryl',
        CreditUserEntity.SURNAME: 'Bryant',
        CreditUserEntity.COUNTRY: 'Italy',
        CreditUserEntity.AGE: 52,
        CreditUserEntity.ADDRESS: '37390 St. Charles Avenue'
    },
    '2XJ6G726': {
        CreditUserEntity.ID: 'c1e6a1b2-d836-4040-a433-08d1c6c9e6f7',
        CreditUserEntity.PID: '2XJ6G726',
        CreditUserEntity.NAME: 'Chris',
        CreditUserEntity.SURNAME: 'Coleman',
        CreditUserEntity.COUNTRY: 'Russia',
        CreditUserEntity.AGE: 55,
        CreditUserEntity.ADDRESS: '35367 St. Charles Avenue'
    },
    '3RT4K524': {
        CreditUserEntity.ID: 'e699e4a4-d868-4a8f-a2c2-11d94f4a3e9a',
        CreditUserEntity.PID: '3RT4K524',
        CreditUserEntity.NAME: 'Randy',
        CreditUserEntity.SURNAME: 'Mitchell',
        CreditUserEntity.COUNTRY: 'United Kingdom',
        CreditUserEntity.AGE: 15,
        CreditUserEntity.ADDRESS: '89597 St. Charles Avenue'
    },
    '5ME1H028': {
        CreditUserEntity.ID: '7e58e04d-f7d7-4a81-bcef-1b0f7d9e9c9e',
        CreditUserEntity.PID: '5ME1H028',
        CreditUserEntity.NAME: 'Joseph',
        CreditUserEntity.SURNAME: 'Wagner',
        CreditUserEntity.COUNTRY: 'Germany',
        CreditUserEntity.AGE: 32,
        CreditUserEntity.ADDRESS: '48007 St. Charles Street'
    },
    '2XV5R979': {
        CreditUserEntity.ID: 'e7f2c2b2-7b3a-4649-a3b3-bfef7a432519',
        CreditUserEntity.PID: '2XV5R979',
        CreditUserEntity.NAME: 'Victor',
        CreditUserEntity.SURNAME: 'Foster',
        CreditUserEntity.COUNTRY: 'Japan',
        CreditUserEntity.AGE: 60,
        CreditUserEntity.ADDRESS: '92881 St. Charles Street'
    },
    '3EE3T946': {
        CreditUserEntity.ID: 'c4ed7f4f-fbef-4a70-a1f2-27a98d040f9a',
        CreditUserEntity.PID: '3EE3T946',
        CreditUserEntity.NAME: 'Gerald',
        CreditUserEntity.SURNAME: 'Evans',
        CreditUserEntity.COUNTRY: 'Canada',
        CreditUserEntity.AGE: 48,
        CreditUserEntity.ADDRESS: '73585 St. Charles Street'},
    '4JW4G084': {
        CreditUserEntity.ID: '1b3c3b09-eb50-4793-bfd4-c18e7a051b2e',
        CreditUserEntity.PID: '4JW4G084',
        CreditUserEntity.NAME: 'Andrea',
        CreditUserEntity.SURNAME: 'Hernandez',
        CreditUserEntity.COUNTRY: 'South Africa',
        CreditUserEntity.AGE: 13,
        CreditUserEntity.ADDRESS: '14648 St. Charles Street'
    },
    '9YW5A564': {
        CreditUserEntity.ID: '9f47c679-c7b1-4f6f-8d1f-6cab3e3e87fc',
        CreditUserEntity.PID: '9YW5A564',
        CreditUserEntity.NAME: 'Arthur',
        CreditUserEntity.SURNAME: 'Bell',
        CreditUserEntity.COUNTRY: 'Australia',
        CreditUserEntity.AGE: 62,
        CreditUserEntity.ADDRESS: '32225 St. Charles Avenue'
    },
    '5NH6G965': {
        CreditUserEntity.ID: 'c25f9f9e-f02d-4b6e-8f3b-6a3bf6aeabca',
        CreditUserEntity.PID: '5NH6G965',
        CreditUserEntity.NAME: 'Kathryn',
        CreditUserEntity.SURNAME: 'Nelson',
        CreditUserEntity.COUNTRY: 'China',
        CreditUserEntity.AGE: 34,
        CreditUserEntity.ADDRESS: '34793 St. Charles Avenue'
    }
}


def map_to_credit_user(map):
    return {
        CreditUserEntity.ID: map[CreditUserEntity.ID],
        CreditUserEntity.PID: map[CreditUserEntity.PID],
        CreditUserEntity.NAME: map[CreditUserEntity.NAME],
        CreditUserEntity.SURNAME: map[CreditUserEntity.SURNAME]
    }


def map_to_credit_user_details(map):
    return {
        CreditUserEntity.ID: map[CreditUserEntity.ID],
        CreditUserEntity.PID: map[CreditUserEntity.PID],
        CreditUserEntity.AGE: map[CreditUserEntity.AGE],
        CreditUserEntity.COUNTRY: map[CreditUserEntity.COUNTRY],
        CreditUserEntity.ADDRESS: map[CreditUserEntity.ADDRESS]
    }


@app.route(
    '/credit-users/<string:pid>/details',
    methods=['GET']
)
def find_by_pid_details(pid):
    credit_user = credit_users.get(f"{pid}")

    if credit_user:
        result = map_to_credit_user_details(credit_user)
        return jsonify(result)
    else:
        return app.response_class(
            status=404
        )


@app.route(
    '/credit-users/<string:pid>',
    methods=['GET']
)
def find_by_pid(pid):
    credit_user = credit_users.get(f"{pid}")

    if credit_user:
        result = map_to_credit_user(credit_user)
        return jsonify(result)
    else:
        return app.response_class(
            status=404
        )


if __name__ == "__main__":
    app.run(
        host=os.getenv("HOST_ADDRESS"),
        debug=True
    )
