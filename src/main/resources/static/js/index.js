const IndexApp = (() => {
    const selectTemplate = (data) => {
        return `<option value="${data.value}">${data.name}</option>`;
    };

    const IndexController = function () {
        const indexService = new IndexService();

        const login = () => {
            const loginBtn = document.getElementById('login-btn');
            loginBtn.addEventListener('click', indexService.login);

        };

        const signUp = () => {
            const signUpBtn = document.getElementById('signup-btn');
            signUpBtn.addEventListener('click', indexService.signUp);
        };

        const init = () => {
            login();
            signUp();
        };

        return {
            init: init,
        };
    };

    const IndexService = function () {
        const indexApi = new IndexApi();

        const login = (event) => {
            event.preventDefault();

            const email = document.getElementById('login-email');
            const password = document.getElementById('login-password');

            const data = {
                userEmail: email.value,
                userPassword: password.value
            };

            indexApi.login(data)
            .then(response => {
                return response.json();
            }).then(json => {
                if (json.hasOwnProperty('errorMessage')) {
                    alert(json.errorMessage);
                } else {
                    location.href='/newsfeed';
                }
            })
        };

        const signUp = (event) => {
            event.preventDefault();

            if(AppStorage.check('sign-up-run')) return;
            AppStorage.set('sign-up-run', true);

            const firstName = document.getElementById('signup-first-name');
            const lastName = document.getElementById('signup-last-name');
            const email = document.getElementById('signup-email');
            const password = document.getElementById('signup-password');

            const data = {
                userName: {
                    firstName: firstName.value,
                    lastName: lastName.value,
                },
                userEmail: email.value,
                userPassword: password.value
            };

            indexApi.signUp(data)
                .then(response => {
                    return response.json();
                }).then(json => {
                    if (json.hasOwnProperty('errorMessage')) {
                        alert(json.errorMessage);
                    } else {
                        firstName.value = "";
                        lastName.value = "";
                        email.value = "";
                        password.value = "";
                        alert('가입을 완료했습니다. 로그인 하세요.');
                    }
                    AppStorage.set('sign-up-run', false);
                })
        };

        return {
            login: login,
            signUp: signUp,
        };
    };

    const IndexApi = function () {
        const login = (data) => {
            return Api.post('/api/signin', data);
        };

        const signUp = (data) => {
            return Api.post('/api/users/signup', data);
        };

        return {
            login: login,
            signUp: signUp,
        }
    };

    const init = () => {
        const indexController = new IndexController();
        indexController.init();
    };

    return {
        init: init,
    };
})();

IndexApp.init();