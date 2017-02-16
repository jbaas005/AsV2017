(function () {
    var opts = [
        [['Administratie klopt niet', 'oneone'],
            ['Adminstratie is verkeerd', 'onetwo']],
        [
            ['Offerte klopt niet', 'twoAone'],
            ['Offerte is verkeerd', 'twoAtwo'],
            ['Offerte is niet verkeerd', 'twoBone'],
            ['Offerte is top', 'twoBtwo']],
        [['Medewerker is niet aardig', 'threeone'],
            ['Medewerker is te aardig', 'threetwo']],
        [['Levering is niet gedaan', 'fourone'],
            ['Levering is wel gedaan', 'fourtwo']],
        [
            ['Bestelling is te laat', 'fiveAone'],
            ['Bestelling is optijd', 'fiveAtwo'],
            ['Bestelling is te vroeg', 'fiveBone'],
            ['Bestelling is veel te vroeg', 'fiveBtwo']],
        [['Ik verveel me gewoon', 'sixone'],
            ['Ik heb zin om te klagen.', 'sixtwo']]
    ];

    document.getElementById('category').onchange = function () {
        setOptions(
                document.getElementById('subCategory'), opts,
                document.getElementById('category').options[document.getElementById('category').selectedIndex].value
                );
    };


    setOptions = function (selbox, opts, chosen) {
        var selbox, i, ii, grp;
        selbox.options.length = 0;
        while (selbox.firstChild)
            selbox.removeChild(selbox.firstChild);
        if (chosen === " ") {
            selbox.options[selbox.options.length] =
                    new Option('Please select one of the options above first', ' ');
        } else {
            for (i = 0, ii = opts[chosen].length; i < ii; i++) {
                if (opts[chosen][i].length > 1) {
                    selbox.options[selbox.options.length] =
                            new Option(opts[chosen][i][0], opts[chosen][i][1]);
                } else {
                    grp = document.createElement('optgroup');
                    grp.label = opts[chosen][i][0];
                    selbox.appendChild(grp);
                }
            }
        }
    };

})();