def call(Map config = [:]) {

    checkout([
        $class: 'GitSCM',
        branches: [[name: "refs/heads/$branch"]],
        userRemoteConfigs: [[url: gitUrl]]
    ])
}