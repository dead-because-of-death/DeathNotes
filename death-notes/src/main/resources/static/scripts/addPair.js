counter = 0;

function addPair() {
    counter++;
    if(counter < 60) {
        $('#dictionaryform').append('Word: <textarea rows=\"1\" cols=\"30\" maxlength=\"50\" minlength=\"1\" class=\"text\" name=\"definitions['+ counter +'].key\"></textarea>' +
            ' Definition: <textarea rows=\"1\" cols=\"50\" maxlength=\"500\" minlength=\"1\" class=\"text\" name=\"definitions['+ counter +'].value\"></textarea>' + '<br><br>');
    }
}