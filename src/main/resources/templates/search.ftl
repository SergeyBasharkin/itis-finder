<#include "base.ftl">
<@base title="Search"/>
<#macro bodyPage>
    <div class="navbar-fixed">
        <nav>
            <div class="nav-wrapper indigo darken-2">
                <form>
                    <div class="input-field">
                        <input id="search" type="search" required>
                        <label class="label-icon" for="search"><i class="material-icons">search</i></label>
                        <i class="material-icons">tune</i>
                    </div>
                </form>
            </div>
        </nav>
    </div>
    <!-- Modal Structure -->
    <div id="modal1" class="modal">
        <div class="modal-content">
            <h4>Search settings</h4>

            <div class="input-field col s12">
                <select>
                    <option value="" disabled selected>Choose your category</option>
                    <option value="1">Option 1</option>
                    <option value="2">Option 2</option>
                    <option value="3">Option 3</option>
                </select>
                <label>Category</label>
            </div>
            <div class="input-field col s12">
                <select>
                    <option value="" disabled selected>Choose your category</option>
                    <option value="1">Option 1</option>
                    <option value="2">Option 2</option>
                    <option value="3">Option 3</option>
                </select>
                <label>Category</label>
            </div>
            <div id="test-slider"></div>
        </div>
        <div class="modal-footer">
            <a href="#!" class="modal-close waves-effect waves-green btn-flat">Agree</a>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <#list libraries as library>
                <div class="col s12">
                    <div class="card ">
                        <div class="card-stacked">
                            <div class="card-content">
                        <span class="card-title">
                            <img width="40" height="40"
                                 src="https://d2j3q9yua85jt3.cloudfront.net/img/ff6fd9c8fca99fb1041c202f3147d577" alt=""
                                 class="circle">
                            <span class="my-card-title ">${library.artifactId}</span>
                            <span class="right usage">82742 usage Version: ${library.version}</span>
                        </span>
                                <p>jsoup is a Java library for working with real-world HTML. It provides a very
                                    convenient
                                    API
                                    for extracting and manipulating data, using the best of DOM, CSS, and jquery-like
                                    methods.</p>
                                <p>${library.groupId}</p>
                            </div>
                            <div class="card-action">
                                <div class="chip">
                                    <img src="https://habr.com/images/favicon-32x32.png" alt="Contact Person">
                                    Habr.com
                                </div>
                                <div class="chip">
                                    <img src="https://proglib.io/wp-content/uploads/2016/10/cropped-favicon-32x32.png"
                                         alt="Contact Person">
                                    ProgLib
                                </div>
                                <div class="chip">
                                    <img src="https://tproger.ru/favicon-32x32.png" alt="Contact Person">
                                    Tproger
                                </div>
                                <div class="chip">
                                    <img src="https://www.mkyong.com/favicon.ico" alt="Contact Person">
                                    Mkyoung
                                </div>
                                <div class="chip">
                                    <img src="http://i.codenet.ru/favicon.ico" alt="Contact Person">
                                    CodeNet
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>

</#macro>