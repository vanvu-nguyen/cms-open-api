package PGPHandler;

public class KeyContainer {
    public static final String PUBLIC_KEY = "-----BEGIN PGP PUBLIC KEY BLOCK-----\n" +
            "Version: BCPG v1.70\n" +
            "\n" +
            "mQENBGUbtt4DCADUQduFwZObacoB9dU0euV5AHaC43LXJnrlEueIKP7bWVBhJUc6\n" +
            "kCi5jGqRmotXgQoJn/GxQ+fLiGz0fDh0eCc588lsWEW48L37tepLnK7O5EdWMyWr\n" +
            "8S36uH9yrynqNzjFZ7rlIDF1Vf0PfyYbFYZ+rn3YXA4hp+QhYiqqhQXdXxCcIhlr\n" +
            "pl8BpTcpsKY+cZkGf0tCGTf/E3q7+9vB1uerq/JJmA65gZRJLf2wfrDvLLAd2h70\n" +
            "+MFknFUVK9yaDpJGqqPXp61g9tvrqMCy1Jl5Q90nvUaA5rq6VEp0kUrbgnOquokQ\n" +
            "w0OCfOIxqsF3UpDHb7jMnwT1yUrjBeqZ+wNpABEBAAG0CklQMTE3ODk0ODOJAS8E\n" +
            "AwMIABkFAmUbtt4CGwMECwkIBwQVCgkIBRYBAwIAAAoJECZu0/ezZo71L14H/RR9\n" +
            "2CR/ff2jArL4WUmKrbS6U3Gh0qePoo4WxwQcXtaZsol5wh+z6Gzc6H8iNRuF9Y4F\n" +
            "CKHP5jBt74QMhtL0yoRmWuQxjghHvVNVmD+ng2mMEQ50kqkJavWLpqi6/ZLpFoY8\n" +
            "SJLt8vxQGp8I/qT5JnvCfdGmsuyXGjMrRnCgiTAaiv8FwRARhPuPug8TDOaLsN78\n" +
            "FgX2SUkXUZr0pDrseV9FmhTMGCBYwI21cSg9Q3tvh4uMiCvQOpbRVKSlQg4294WQ\n" +
            "oS9vJw+WgRztQnrSf83Rfo2jI+Jaui+ciCzLgW0mJorqZ2g2JEMmbF5dg48kWMjB\n" +
            "lM/Qbv3T3GZbNouEKGC5AQ0EZRu23gIIAJv3DHkPEsxnrbYEtQnbtdbIliOm7dyZ\n" +
            "OwdMNgOpstxREcsff3Cw1Mx/9+mFnGpAgerdSx6LqUww14i8Lpxosj6tq5/an382\n" +
            "aFVKL3xPS6Dxzk3anAUlBqabchB1zh/XI84hCrrebREjITMnF6QYww1WTSgekAlG\n" +
            "fnR9tSZVmWu2dZsqz2hdt9JzgJ0OmJDe1e0s6mC4iX755EJ5YKKTP3G4UbXcQRr3\n" +
            "B2icA8odtLsTj2rBFFogJyWzU8UiHlCUtJVhsbfxVxW5s3ofQPElBGsym6WSEV3Q\n" +
            "3r6Ta1N3UQfEFbfSxu5N8QwN1ZM8k0xsgUyRcVHVGp+cbLkYz5LIa6EAEQEAAYkB\n" +
            "HwQYAwgACQUCZRu3AAIbDAAKCRAmbtP3s2aO9bRiB/9StMBkf7B7yPLzN8y7fTQD\n" +
            "9UWmeRMjv36TSgv6Dh3oCNmR/da6WX9v82G5JtwJPVCzVtCLWHgGoMoGwxXOLL5i\n" +
            "wwOyFiQB3tozj9fIPHEfA0JGIpytOZQv5V2EHVsJED/skcX+HIHpo3kqvDLqmyKD\n" +
            "EM4+FhbxQeRQlzNNIUg4RGpTVbiSCcrMdVRAZ24tOT3dHbvO24B2Le3BQQOLScPj\n" +
            "DgnHZXTGXCcnD4bR9rLmqjN7BxM6v7YKckou8bQqTUwZHu1y6ICd0rBUGurDQJa0\n" +
            "CxcVWxJaW9JwVX/Wm6V1w22H3zPoGIyCrhunpPJ/AoSVAlArjH+u5bAwMm8j8RIU\n" +
            "=IPE/\n" +
            "-----END PGP PUBLIC KEY BLOCK-----";

    public static final String PRIVATE_KEY = "-----BEGIN PGP PRIVATE KEY BLOCK-----\n" +
            "Version: BCPG v1.70\n" +
            "\n" +
            "lQPGBGUbtt4DCADUQduFwZObacoB9dU0euV5AHaC43LXJnrlEueIKP7bWVBhJUc6\n" +
            "kCi5jGqRmotXgQoJn/GxQ+fLiGz0fDh0eCc588lsWEW48L37tepLnK7O5EdWMyWr\n" +
            "8S36uH9yrynqNzjFZ7rlIDF1Vf0PfyYbFYZ+rn3YXA4hp+QhYiqqhQXdXxCcIhlr\n" +
            "pl8BpTcpsKY+cZkGf0tCGTf/E3q7+9vB1uerq/JJmA65gZRJLf2wfrDvLLAd2h70\n" +
            "+MFknFUVK9yaDpJGqqPXp61g9tvrqMCy1Jl5Q90nvUaA5rq6VEp0kUrbgnOquokQ\n" +
            "w0OCfOIxqsF3UpDHb7jMnwT1yUrjBeqZ+wNpABEBAAH+BwMCz/Mjfr2bgXRgn2J4\n" +
            "YG+6v/1wXKPUargBTxGNUDuUVgATdalp0RidsWYm5CT2/8fQtTl4bYBMKWkw9Qje\n" +
            "Bol6JQhNL8so/lncKKrZMUwoxwniOu+MlfH6CGwvAyWTrMX+nsGBziajqRfZJlbk\n" +
            "52HHKmzeOWJekz9a5tWpeT60J9fVHFKLmrxj/VIXmXmIAKT1Jd7IKwXKyXNHyGh0\n" +
            "Pd+SPYAzLwZ4TIw+NLK0Mkp37c046MCknAajO3aogC8fSUz+O7OVB7oSSXlMSNoI\n" +
            "uCiou3smawca50kftYAodD6B4XHVknZEa9IWb+C7KWHnUL8IE0qUimP/NuCvNKls\n" +
            "B4Xm6BYm0EvPNMg6gUI9S2bgU7ZZjyioP4A7UUCSvhP+y/prj0SUbrgFwlC9dNAC\n" +
            "eoL3/Vp3qTeFATOHtjSjzaRoe1w6kr6vOCG2xWQRxqL4etfbre8qOatro5aE9U05\n" +
            "kLWqVpRzuGNPhP6NqTTV2LiGIqGMSxghIZmbI35gU2OGOcmsNxxJfgUXj9pk9MyS\n" +
            "eVh/tf5Bg+NNmdoKdn7szTPInJQf2ptLudtENz0Uyz6O9MMJFZW+laEMlL/se8LT\n" +
            "X7lE6A2tTzpbPLA0YC6T0M9Epp/GMVAi6Dc/ftkbZwL7Fs+1QrQ/iHlYBKX4/YVm\n" +
            "OjND+4Zn0SFkzPr2Cos2t8StjlPhfseKUb0AQ2F9dyYWKhWSX77gu0kYkQW0h+H+\n" +
            "z08QeGH88c1V3f5Ouks1AXOjsgdF3GrZJkjJ0gLriO97aKX/olm3Gkq4NgwqepwW\n" +
            "uwvO8mlioAzPhNfb95mK3YdmFXiFc0SFUagEPs1P/znaIi/LPuPtJzJnqFhdu7ZE\n" +
            "7+HfUZCbIpHhfP4bCUo974YiAAtWnMYxV6HyMNcK/TpI5cmifoW6IWjn92Heg1pi\n" +
            "FcbQcp5TD2gctApJUDExNzg5NDgziQEvBAMDCAAZBQJlG7beAhsDBAsJCAcEFQoJ\n" +
            "CAUWAQMCAAAKCRAmbtP3s2aO9S9eB/0Ufdgkf339owKy+FlJiq20ulNxodKnj6KO\n" +
            "FscEHF7WmbKJecIfs+hs3Oh/IjUbhfWOBQihz+Ywbe+EDIbS9MqEZlrkMY4IR71T\n" +
            "VZg/p4NpjBEOdJKpCWr1i6aouv2S6RaGPEiS7fL8UBqfCP6k+SZ7wn3RprLslxoz\n" +
            "K0ZwoIkwGor/BcEQEYT7j7oPEwzmi7De/BYF9klJF1Ga9KQ67HlfRZoUzBggWMCN\n" +
            "tXEoPUN7b4eLjIgr0DqW0VSkpUIONveFkKEvbycPloEc7UJ60n/N0X6NoyPiWrov\n" +
            "nIgsy4FtJiaK6mdoNiRDJmxeXYOPJFjIwZTP0G7909xmWzaLhChgnQPGBGUbtt4C\n" +
            "CACb9wx5DxLMZ622BLUJ27XWyJYjpu3cmTsHTDYDqbLcURHLH39wsNTMf/fphZxq\n" +
            "QIHq3Usei6lMMNeIvC6caLI+rauf2p9/NmhVSi98T0ug8c5N2pwFJQamm3IQdc4f\n" +
            "1yPOIQq63m0RIyEzJxekGMMNVk0oHpAJRn50fbUmVZlrtnWbKs9oXbfSc4CdDpiQ\n" +
            "3tXtLOpguIl++eRCeWCikz9xuFG13EEa9wdonAPKHbS7E49qwRRaICcls1PFIh5Q\n" +
            "lLSVYbG38VcVubN6H0DxJQRrMpulkhFd0N6+k2tTd1EHxBW30sbuTfEMDdWTPJNM\n" +
            "bIFMkXFR1RqfnGy5GM+SyGuhABEBAAH+BwMCz/Mjfr2bgXRg1sOmErd1Ori1hUhg\n" +
            "aHvYoRmFpgrSWgJ2jsFkIakqp1HgOrqbg++W4vYKdo1FpnCTrVFEddH0ugHB39vl\n" +
            "uRPQ65hSe3sC2zUM87dwo5rhMoC1B+yQbgq2OSxZg9g03kzgqXIqVo7ChtNSwzqp\n" +
            "Ji90yOAp+KoWiAVG6RWbl8vhNI7OV9uVNFq131MIiu4Ta7yD4Va0DOYc6LsncIf6\n" +
            "bk+n9b/PZkh1IQEhQJbseS4F/shHAZdd49K8wCbn39RPWAfsbKncHM+ShO7i7gL6\n" +
            "g0c2+1ebuAkJQLaUjLgTU799W4g9WicU+16U0kdS8FI8WBDDmmZKfK4sld5bYNMC\n" +
            "n22ezRP8MIhG/7vepJBrBduA0hytHlnAKr4DrUBgxrHDf1VH6WwMY8fMhL1vgE+k\n" +
            "Xd+YyT5tJVH3zoNqmk4VDcMQHFDffTD+3h6Dfbjtc2uCyhK9137UtGvnqegiTgc1\n" +
            "CR2ET2VjNTYFWZ+or+UQbqdKqCDybRzRPt4klNEZCc5HzldykFilcP3gTFo86gne\n" +
            "ec9CjhirAirSbYpWbAidx2R4mDCNhK2pEAtg+uJpQDvfS+LXQ9KQK421UhQ2V23s\n" +
            "JjHB0OqJeNiEvKAEPoQsw1pOWMhnGG8HQ52mthVVi6EdgIcx5awjoT/Q0PQQRASL\n" +
            "+C013NFwOwKHRBdsgEitlvz/bCOBjlUu8Y9HhAgiA8qB904V33D79EZMssZ3amSg\n" +
            "IPPmD+Dd/vIMo9K1RZ80wwwaA10HjooEh9VUPDxm6m4gXwmYp9aXzS40dukY8h4U\n" +
            "D331CX09/CO1mTyTnziIdEE7m96zb9AP4tjAuLNSx0itckcAxnAaqdHthGfKikww\n" +
            "0K8l7r3vAjc6Zi+stMcqTTDCE5uZ4pv/6D+OPBrsHXJ5vMvR5ka6dgY0XxxwmQh9\n" +
            "iQEfBBgDCAAJBQJlG7cAAhsMAAoJECZu0/ezZo71tGIH/1K0wGR/sHvI8vM3zLt9\n" +
            "NAP1RaZ5EyO/fpNKC/oOHegI2ZH91rpZf2/zYbkm3Ak9ULNW0ItYeAagygbDFc4s\n" +
            "vmLDA7IWJAHe2jOP18g8cR8DQkYinK05lC/lXYQdWwkQP+yRxf4cgemjeSq8Muqb\n" +
            "IoMQzj4WFvFB5FCXM00hSDhEalNVuJIJysx1VEBnbi05Pd0du87bgHYt7cFBA4tJ\n" +
            "w+MOCcdldMZcJycPhtH2suaqM3sHEzq/tgpySi7xtCpNTBke7XLogJ3SsFQa6sNA\n" +
            "lrQLFxVbElpb0nBVf9abpXXDbYffM+gYjIKuG6ek8n8ChJUCUCuMf67lsDAybyPx\n" +
            "EhQ=\n" +
            "=3ADn\n" +
            "-----END PGP PRIVATE KEY BLOCK-----\n";

    public static final String PASS_CODE = "Info@Plus";
}
